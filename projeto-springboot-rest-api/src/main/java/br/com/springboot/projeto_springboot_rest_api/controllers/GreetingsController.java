package br.com.springboot.projeto_springboot_rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    
    /**
     * Temos um método do controlador que recebe uma requisição HTTP do tipo POST na rota "/salvar". O 
     * objetivo desse método é salvar um objeto Usuario no banco de dados e retornar uma resposta HTTP personalizada.
     * 
     * Vamos analisar o script abaixo:
     * 
     * @PostMapping(value = "salvar"): Essa anotação é usada para mapear a rota "/salvar" ao método salvar(). Ela indica que o método 
     * deve ser executado quando uma requisição POST é feita para a rota "/salvar".
     * 
     * @ResponseBody: Essa anotação indica que o valor retornado pelo método deve ser colocado diretamente no corpo da resposta HTTP, em 
     * vez de ser interpretado como o nome de uma view (página HTML) a ser renderizada.
     * 
     * public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario): Esse é o método salvar(), que recebe um objeto Usuario no 
     * corpo da requisição HTTP. O parâmetro @RequestBody indica que o objeto Usuario é esperado no corpo da requisição.
     * 
     * Usuario user = usuarioRepository.save(usuario);: Aqui, o objeto usuario recebido na requisição é salvo no banco de dados usando o 
     * repositório usuarioRepository. O método save() é usado para persistir o objeto no banco de dados e retorna o objeto Usuario salvo, 
     * que é armazenado na variável user.
     * 
     * return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);: Nesta linha, é criada uma instância da classe ResponseEntity 
     * contendo o objeto Usuario salvo (armazenado em user) e o status HTTP "201 Created". A classe ResponseEntity permite que você crie 
     * uma resposta HTTP personalizada, onde você pode definir o corpo, os cabeçalhos e o status da resposta.
     * 
     * Em resumo, esse método salvar() recebe um objeto Usuario no corpo da requisição POST, salva-o no banco de dados usando o 
     * repositório usuarioRepository e retorna uma resposta HTTP contendo o objeto Usuario salvo com o status "201 Created". Isso indica 
     * que a operação de salvamento foi bem-sucedida e que um novo recurso (usuário) foi criado no servidor.
     * */
    
    // http://localhost:8000/salvar
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
       Usuario user = usuarioRepository.save(usuario);
       
       return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    
    
    /**
     * Temos um método do controlador que recebe uma requisição HTTP do tipo DELETE na rota "/deletar". O objetivo desse método é deletar 
     * um usuário com base no ID fornecido e retornar uma resposta HTTP personalizada.
     * 
     * Vamos analisar o script abaixo:
     * 
     * @DeleteMapping(value = "deletar"): Essa anotação é usada para mapear a rota "/deletar" ao método deletar(). Ela indica que o 
     * método deve ser executado quando uma requisição DELETE é feita para a rota "/deletar".
     * 
     * @ResponseBody: Essa anotação indica que o valor retornado pelo método deve ser colocado diretamente no corpo da resposta HTTP, em 
     * vez de ser interpretado como o nome de uma view (página HTML) a ser renderizada.
     * 
     * public ResponseEntity<String> deletar(@RequestParam Long iduser): Esse é o método deletar(), que recebe o parâmetro iduser como um parâmetro de consulta (query parameter) da requisição HTTP. Esse parâmetro deve ser fornecido na URL da requisição, por exemplo, "/deletar?iduser=1".
     * 
     * usuarioRepository.deleteById(iduser);: Aqui, o usuário com o ID fornecido é deletado do banco de dados usando o repositório 
     * usuarioRepository. O método deleteById() é usado para deletar um registro com base no ID fornecido.
     * 
     * return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);: Nesta linha, é criada uma instância da classe 
     * ResponseEntity contendo a mensagem "Usuário deletado com sucesso" e o status HTTP "200 OK". A classe ResponseEntity permite que 
     * você crie uma resposta HTTP personalizada, onde você pode definir o corpo, os cabeçalhos e o status da resposta.
     * 
     * Em resumo, esse método deletar() recebe um parâmetro iduser contendo o ID do usuário a ser deletado, deleta o usuário 
     * correspondente do banco de dados usando o repositório usuarioRepository e retorna uma resposta HTTP contendo a mensagem "Usuário 
     * deletado com sucesso" com o status "200 OK". Isso indica que a operação de deleção foi bem-sucedida.
     * */
    
    // http://localhost:8000/deletar
    @DeleteMapping(value = "deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long iduser){
       usuarioRepository.deleteById(iduser);
       
       return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    
    /**
     * Temos um método do controlador que recebe uma requisição HTTP do tipo GET na rota "/buscaruserid". O objetivo desse método é 
     * buscar um usuário no banco de dados com base no ID fornecido e retornar uma resposta HTTP contendo os dados do usuário encontrado.
     * 
     * Vamos explicar cada parte do script:
     * 
     * @GetMapping(value = "buscaruserid"): Essa anotação é usada para mapear a rota "/buscaruserid" ao método buscaruserid(). Ela indica 
     * que o método deve ser executado quando uma requisição GET é feita para a rota "/buscaruserid".
     * 
     * @ResponseBody: Essa anotação indica que o valor retornado pelo método deve ser colocado diretamente no corpo da resposta HTTP, em 
     * vez de ser interpretado como o nome de uma view (página HTML) a ser renderizada.
     * 
     * public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser): Esse é o método buscaruserid(), que 
     * recebe o parâmetro iduser como um parâmetro de consulta (query parameter) da requisição HTTP. Esse parâmetro deve ser fornecido na 
     * URL da requisição, por exemplo, "/buscaruserid?iduser=1".
     * 
     * Usuario usuario = usuarioRepository.findById(iduser).get();: Aqui, o usuário é buscado no banco de dados usando o repositório 
     * usuarioRepository e o método findById(). O método findById() retorna um objeto do tipo Optional<Usuario>, que contém o usuário 
     * encontrado ou é vazio se nenhum usuário com o ID fornecido for encontrado. O método get() é usado para obter o usuário do 
     * Optional<Usuario>.
     * 
     * return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);: Nesta linha, é criada uma instância da classe ResponseEntity contendo 
     * o usuário encontrado e o status HTTP "200 OK". A classe ResponseEntity permite que você crie uma resposta HTTP personalizada, onde 
     * você pode definir o corpo, os cabeçalhos e o status da resposta.
     * 
     * Em resumo, esse método buscaruserid() recebe um parâmetro iduser contendo o ID do usuário a ser buscado, busca o usuário 
     * correspondente no banco de dados usando o repositório usuarioRepository e retorna uma resposta HTTP contendo os dados do usuário 
     * encontrado com o status "200 OK". Isso indica que a operação de busca foi bem-sucedida.
     * */
    
    // http://localhost:8000/buscaruserid
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){
      Usuario usuario =  usuarioRepository.findById(iduser).get(); 
       return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}
