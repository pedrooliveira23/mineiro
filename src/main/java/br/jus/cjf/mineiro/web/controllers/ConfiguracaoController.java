package br.jus.cjf.mineiro.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.mineiro.model.Resposta;
import br.jus.cjf.mineiro.service.EmailService;

@Controller
public class ConfiguracaoController {

	private final EmailService emailService;

	@Autowired
	public ConfiguracaoController(EmailService emailService) {
		super();
		this.emailService = emailService;
	}

	@RequestMapping(value = "/admin/configuracao/email/teste", method = RequestMethod.GET)
	public @ResponseBody
	Resposta mostrarConfiguracaoEmailTeste(Map<String, Object> model) {

		return emailService.gerarAlertaTeste();
	}

	@RequestMapping(value = "/admin/configuracao", method = RequestMethod.GET)
	public String listarConfiguracao(Map<String, Object> model) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MineiroConfiguration.class);
		ctx.refresh();
		ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);

		model.put("configuracaoMineiro", configuracaoMineiro);
		ctx.close();
		return "listarConfiguracao";
	}

}
