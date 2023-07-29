package br.com.springboot.projeto_springboot_rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    
    /**
     * Vamos analisar o script abaixo:
     * 
     * 1. `@GetMapping(value = "listatodos")`: Essa é uma anotação do Spring Framework que mapeia a requisição HTTP GET para a URL 
     * especificada. Neste caso, a URL mapeada é "listatodos". Portanto, quando uma requisição GET é feita para essa URL, o método 
     * `listaUsuarios()` será executado.
     * 
     * 2. `@ResponseBody`: Essa anotação indica que o valor de retorno do método deve ser convertido em JSON e incluído no corpo da 
     * resposta HTTP. Isso é útil quando estamos construindo uma API REST e desejamos retornar dados no formato JSON.
     * 
     * 3. `public ResponseEntity<List<Usuario>> listaUsuarios()`: Este é o método que é invocado quando a requisição GET é feita para a 
     * URL "listatodos". Ele retorna um objeto `ResponseEntity<List<Usuario>>`, que encapsula a resposta HTTP.
     * 
     * 4. `List<Usuario> usuarios = usuarioRepository.findAll()`: Nesta linha, o método `findAll()` do `usuarioRepository` é chamado. 
     * Presumivelmente, o `usuarioRepository` é um objeto que implementa a interface `JpaRepository` ou uma de suas subinterfaces. O 
     * método `findAll()` retorna uma lista de todos os objetos do tipo `Usuario` armazenados no banco de dados.
     * 
     * 5. `return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK)`: Por fim, o método cria um novo objeto `ResponseEntity` 
     * contendo a lista de usuários obtida do banco de dados e define o status HTTP da resposta como "OK" (código 200). O objeto 
     * `ResponseEntity` permite que você defina o corpo, cabeçalhos e status da resposta HTTP que será enviada de volta ao cliente.
     * 
     * Em resumo, esse script representa um endpoint de uma API REST que retorna uma lista de usuários em formato JSON quando a requisição 
     * GET é feita para a URL "listatodos". A lista de usuários é obtida do banco de dados usando o `usuarioRepository` e é enviada de 
     * volta ao cliente como parte do corpo da resposta HTTP.
     * 
     * */
    
    // http://localhost:8000/listatodos
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuarios(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
}
