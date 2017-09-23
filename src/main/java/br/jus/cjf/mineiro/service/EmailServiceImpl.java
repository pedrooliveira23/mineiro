package br.jus.cjf.mineiro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Resposta;
import br.jus.cjf.mineiro.model.Transicao;

@Service
public class EmailServiceImpl implements EmailService {

	private final MailSender mailSender;

	private final JavaMailSender javaMailSender;

	private final VelocityEngine velocityEngine;

	private final OrdemServicoService ordemServicoService;

	private final TransicaoService transicaoService;

	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	public EmailServiceImpl(MailSender mailSender, JavaMailSender javaMailSender, VelocityEngine velocityEngine, OrdemServicoService ordemServicoService, TransicaoService transicaoService) {
		super();
		this.mailSender = mailSender;
		this.javaMailSender = javaMailSender;
		this.velocityEngine = velocityEngine;
		this.ordemServicoService = ordemServicoService;
		this.transicaoService = transicaoService;
	}

	@Scheduled(cron = "0 1 9 * * MON")
	@Override
	@Transactional("mineiroTransactionManager")
	public void gerarAlertas() {

		List<OrdemServico> ordensServico = ordemServicoService.listarOrdemServicoVerificacao();
		logger.debug("Verificação - Análise Iniciada");
		for (OrdemServico ordemServico : ordensServico) {
			logger.debug("Ordem de serviço da demanda #" + ordemServico.getDemanda().getRedmineIssueId() + " - Análise Iniciada");

			Transicao ultimaTransicao = transicaoService.getTransicaoMaisAtualizada(ordemServico.getDemanda().getRedmineIssueId());

			if (transicaoService.contaTempoCJF(ultimaTransicao) && ultimaTransicao.getDuracaoContandoTempo().getValor().isLongerThan(Duration.standardHours(10))) {

				enviarEmailOrdemServicoParada(ordemServico, ultimaTransicao);
				logger.debug("Ordem de serviço da demanda #" + ordemServico.getDemanda().getRedmineIssueId() + " - Parada no CJF");

			}
			logger.debug("Ordem de serviço da demanda #" + ordemServico.getDemanda().getRedmineIssueId() + " - Análise Finalizada");
		}
		logger.debug("Verificação - Análise Finalizada");

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Resposta gerarAlertaTeste() {

		Resposta resposta = new Resposta();
		MimeMessagePreparator preparator = null;
		logger.debug("Verificação - Email de Teste -Iniciado");

		try {
			preparator = new MimeMessagePreparator() {

				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void prepare(MimeMessage mimeMessage) throws Exception {

					AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

					ctx.register(MineiroConfiguration.class);
					ctx.refresh();

					ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);

					ctx.close();
					String destinatario = configuracaoMineiro.getDestinatarioEmailTeste();
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					if (!configuracaoMineiro.getDestinatarioEmailTeste().equals("sesup@cjf.jus.br")) {
						destinatario = "sesup@cjf.jus.br";
					}

					message.setTo(destinatario);
					message.setFrom(configuracaoMineiro.getRemetenteEmail());
					message.setSubject(configuracaoMineiro.getTituloEmailTeste());
					Map model = new HashMap();
					model.put("configuracaoMineiro", configuracaoMineiro);
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email-teste-template.vm", "utf8", model);

					message.setText(text, true);
				}
			};
			this.javaMailSender.send(preparator);

			logger.debug("Verificação - Email de Teste - Finalizado");

			resposta.setSucesso(true);
			resposta.setMensagem("Email de teste enviado com sucesso!");
			return resposta;

		} catch (Exception e) {
			logger.debug("Verificação - Email de Teste - Falha no envio");
			resposta.setSucesso(false);
			resposta.setMensagem("Falha no envio do email de teste!");
			return resposta;
		}

	}

	@Transactional("mineiroTransactionManager")
	public void enviarEmailOrdemServicoParada(final OrdemServico ordemServico, final Transicao ultimaTransicao) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Integer diasCorridos = (int) ultimaTransicao.getDuracaoTotal().getValor().getStandardDays();
				Integer diasContandoPrazo = null;
				if (ordemServico.getDemanda().getTipoDemanda().getNome().contains("Manutenção Corretiva")) {
					diasContandoPrazo = diasCorridos;
				} else {

					diasContandoPrazo = (int) ultimaTransicao.getDuracaoContandoTempo().getValor().getStandardHours() / 10;
				}

				AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

				ctx.register(MineiroConfiguration.class);
				ctx.refresh();

				ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);
				ctx.close();
				String titulo = configuracaoMineiro.getTituloEmail().replace("#OS", "#" + ordemServico.getDemanda().getRedmineIssueId());
				if (configuracaoMineiro.getAmbienteMineiro().contains("Homologação") || configuracaoMineiro.getAmbienteMineiro().contains("Desenvolvimento")) {
					titulo = titulo.replace("Mineiro", "Mineiro(" + configuracaoMineiro.getAmbienteMineiro() + ")");
				}

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(configuracaoMineiro.getDestinatarioEmail());
				message.setFrom(configuracaoMineiro.getRemetenteEmail());
				message.setSubject(titulo);
				Map model = new HashMap();
				model.put("ordemServico", ordemServico);
				model.put("diasCorridos", diasCorridos);
				model.put("diasContandoPrazo", diasContandoPrazo);
				model.put("estado", ultimaTransicao.getEstado().getNome());
				model.put("configuracaoMineiro", configuracaoMineiro);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email-os-parada-template.vm", "utf8", model);

				message.setText(text, true);
			}
		};
		this.javaMailSender.send(preparator);
	}

	public void enviarEmailSimples(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
