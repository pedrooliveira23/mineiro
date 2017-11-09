package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.service.ContratoService;
import br.jus.cjf.simus.model.Usuario;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"versao","ano"})
public class CadastroController {

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String mostrarPaginaCadastro(Map<String, Object> model) {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

            ctx.register(MineiroConfiguration.class);
            ctx.refresh();

            ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);

            ctx.close();
            String versao = configuracaoMineiro.getVersao();
            String ano = ""+DateTime.now().getYear();

            model.put("ano", ano);
            model.put("contrato", new Contrato());
            model.put("usuario", new Usuario());
            model.put("versao", versao);
            return "cadastro";
	}

}
