package br.com.springboot.projeto_springboot_rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.projeto_springboot_rest_api.model.Usuario;
import br.com.springboot.projeto_springboot_rest_api.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    
    // http://localhost:8000/cadastrarusuario/Daniel/34
    @RequestMapping(value = "/cadastrarusuario/{nome}/{idade}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String cadastrarUsuario(@PathVariable String nome, @PathVariable int idade) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuario.setIdade(idade);
    	
    	usuarioRepository.save(usuario);
    
    	return "Nome: " + nome + " -> idade: " + idade;
    }
    
    
}
