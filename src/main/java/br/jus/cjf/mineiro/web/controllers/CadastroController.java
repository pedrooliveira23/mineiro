package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.DiaNaoUtil;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"versao","ano"})
public class CadastroController {

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String mostrarPaginaCadastro() {
            return "cadastro";
	}

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String realizarCadastro (@Valid Usuario novoUsuario, Map<String, Object> model) {
	    int numeroMatricula = (int) Math.random()%100000;
	    novoUsuario.setId(numeroMatricula);
	    novoUsuario.setMatricula(Integer.toString(numeroMatricula));
	    model.put("novoUsuario", novoUsuario);
	    return "autenticacao";
    }

}
