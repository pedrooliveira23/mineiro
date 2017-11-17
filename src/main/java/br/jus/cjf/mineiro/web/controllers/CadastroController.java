package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.service.UsuarioService;
import br.jus.cjf.simus.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes({"versao", "ano"})
public class CadastroController {

    private final UsuarioService usuarioService;

    @Autowired
    public CadastroController(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String mostrarPaginaCadastro() {
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String realizarCadastro(@Valid Usuario novoUsuario, Map<String, Object> model) {
        model.put("usuario", novoUsuario);
        model.put("contrato", new Contrato());
        usuarioService.gerarMatricula(novoUsuario);
        usuarioService.criarUsuario(novoUsuario);
        model.put("sucesso", true);
        model.put("matricula", novoUsuario.getMatricula());
        return "cadastro";
    }
}
