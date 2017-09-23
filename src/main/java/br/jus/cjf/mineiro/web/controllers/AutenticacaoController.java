package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.service.ContratoService;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.simus.model.Usuario;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"versao","ano"})
public class AutenticacaoController {

     @Autowired
     private ContratoService contratoService;
	
	@RequestMapping(value = "/autenticacao", method = RequestMethod.GET)
	public String mostrarPaginaLogin(Map<String, Object> model) {
            
            
            
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
            return "autenticacao";
	}
	
      
        
	@RequestMapping(value = "/autenticacao/error", method = RequestMethod.GET)
	public String mostrarPaginaErrorLogin(Usuario usuario,Map<String, Object> model) {
	    model.put("contrato", new Contrato());	
            model.put("usuario", usuario);
		model.put("error", true);
		return "autenticacao";
	}
	
    @RequestMapping(value = "/autenticacao/sessaoInvalida", method = RequestMethod.GET)
    public String invalidarSessao() {
        return "redirect:/autenticacao";
    }

     @ModelAttribute("contratos")
	public List<Contrato> contratos() {
		return contratoService.listarContratos();
	}
        


}
