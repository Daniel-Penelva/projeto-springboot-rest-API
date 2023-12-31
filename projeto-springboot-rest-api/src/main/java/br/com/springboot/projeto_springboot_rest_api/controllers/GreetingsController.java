package br.com.springboot.projeto_springboot_rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
	/*
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }*/
    
    // http://localhost:8000/projeto-springboot-rest-api/cadastrarusuario/Daniel/34
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
    
    // http://localhost:8000/projeto-springboot-rest-api/listatodos
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
    
    // http://localhost:8000/projeto-springboot-rest-api/salvar
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
    
    // http://localhost:8000/projeto-springboot-rest-api/deletar
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
    
    // http://localhost:8000/projeto-springboot-rest-api/buscaruserid
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){
      Usuario usuario =  usuarioRepository.findById(iduser).get(); 
       return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    
    /**
     *Temos um método do controlador que recebe uma requisição HTTP do tipo PUT na rota "/atualizar". O objetivo desse método é atualizar 
     *os dados de um usuário no banco de dados com base nos dados fornecidos na requisição e retornar uma resposta HTTP indicando o 
     *resultado da operação.
     *
     *Vamos explicar cada parte do script:
     *
     *@PutMapping(value = "atualizar"): Essa anotação é usada para mapear a rota "/atualizar" ao método atualizar(). Ela indica que o 
     *método deve ser executado quando uma requisição PUT é feita para a rota "/atualizar".
     *
     *@ResponseBody: Essa anotação indica que o valor retornado pelo método deve ser colocado diretamente no corpo da resposta HTTP, em 
     *vez de ser interpretado como o nome de uma view (página HTML) a ser renderizada.
     *
     *public ResponseEntity<?> atualizar(@RequestBody Usuario usuario): Esse é o método atualizar(), que recebe o objeto usuario como 
     *corpo (body) da requisição HTTP. O objeto usuario é convertido a partir dos dados JSON fornecidos no corpo da requisição. A 
     *anotação @RequestBody faz essa conversão automaticamente.
     *
     *if(usuario.getId() == null) { ... }: Nessa parte do código, é verificado se o objeto usuario contém um ID. Se o ID for nulo, isso 
     *significa que o usuário ainda não está persistido no banco de dados, ou seja, é um novo usuário que está sendo criado e não pode 
     *ser atualizado. Nesse caso, o método retorna uma resposta HTTP com status "200 OK" e uma mensagem informando que o ID não foi 
     *informado.
     *
     *Usuario user = usuarioRepository.saveAndFlush(usuario);: Se o ID do usuário não for nulo, o método continua e salva o usuário 
     *atualizado no banco de dados usando o repositório usuarioRepository e o método saveAndFlush(). O método saveAndFlush() salva o 
     *usuário e imediatamente sincroniza os dados com o banco de dados. Isso é útil quando você quer garantir que a operação de 
     *salvamento seja realizada imediatamente.
     *
     *return new ResponseEntity<Usuario>(user, HttpStatus.OK);: Nesta linha, é criada uma instância da classe ResponseEntity contendo o 
     *usuário atualizado e o status HTTP "200 OK". A classe ResponseEntity permite que você crie uma resposta HTTP personalizada, onde 
     *você pode definir o corpo, os cabeçalhos e o status da resposta.
     *
     *Em resumo, esse método atualizar() recebe um objeto usuario contendo os dados atualizados do usuário, verifica se o usuário já 
     *possui um ID (ou seja, já existe no banco de dados), salva o usuário atualizado no banco de dados e retorna uma resposta HTTP com 
     *o usuário atualizado e o status "200 OK". Se o ID do usuário for nulo, o método retorna uma resposta informando que o ID não foi 
     *informado.
     * */
    
    // http://localhost:8000/projeto-springboot-rest-api/atualizar
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não informado!", HttpStatus.OK);
    	}
    	
       Usuario user = usuarioRepository.saveAndFlush(usuario);
       return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    
    /**
     * Vamos analisar esse script:
     * 
     * @GetMapping(value = "buscarPorNome"): Essa é a anotação @GetMapping, que indica que esse método será acionado quando houver uma 
     * requisição HTTP GET para o caminho /buscarPorNome. Em outras palavras, quando o servidor receber uma solicitação GET para esse 
     * caminho, este método será executado.
     * 
     * @ResponseBody: Essa é outra anotação que indica que o valor retornado pelo método será serializado diretamente para o corpo da 
     * resposta HTTP. Neste caso, o método retorna uma lista de objetos Usuario, e a anotação @ResponseBody garante que essa lista será 
     * convertida em JSON e enviada como resposta da requisição.
     * 
     * public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome): Esse é o método buscarPorNome que 
     * será acionado quando a requisição GET for feita para /buscarPorNome. Ele recebe um parâmetro nome, que será fornecido na URL da 
     * requisição como um parâmetro de consulta. A anotação @RequestParam indica que esse parâmetro será extraído da URL.
     * 
     * List<Usuario> usuario = usuarioRepository.buscarByName(nome);: Nesta linha, o método buscarByName é chamado no repositório 
     * usuarioRepository para buscar usuários cujo nome corresponda ao valor fornecido como parâmetro nome. O resultado dessa consulta é 
     * armazenado na lista usuario. O método trim() é usado para remover quaisquer espaços em branco no início e no final do valor 
     * fornecido como parâmetro, evitando problemas de busca por espaços desnecessários. toUpperCase(): O método toUpperCase() é um 
     * método da classe String que converte todos os caracteres da string em letras maiúsculas. Isso é feito para garantir que a consulta 
     * seja insensível a maiúsculas/minúsculas, ou seja, para que a busca não diferencie letras maiúsculas de minúsculas ao comparar o 
     * nome fornecido com os nomes armazenados no banco de dados.
     * 
     * return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);: Finalmente, a lista de usuários encontrada é retornada como 
     * resposta da requisição HTTP. A classe ResponseEntity permite que você defina a resposta HTTP completa, incluindo o corpo da 
     * resposta e o status de resposta. Neste caso, a lista de usuários é definida como corpo da resposta e o status é definido como 
     * HttpStatus.OK (código 200), indicando que a requisição foi bem-sucedida.
     * 
     * Em resumo, esse método buscarPorNome é responsável por buscar usuários no banco de dados cujo nome corresponda ao valor fornecido 
     * como parâmetro nome na URL da requisição. A lista de usuários encontrada é retornada como uma resposta HTTP em formato JSON. Isso 
     * permite que os clientes façam uma solicitação GET para /buscarPorNome com o parâmetro nome na URL e obtenham a lista de usuários 
     * correspondentes como resposta.
     * */
    
    // http://localhost:8000/projeto-springboot-rest-api/buscarPorNome
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){
      List<Usuario> usuario =  usuarioRepository.buscarByName(nome.trim().toUpperCase());
       return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
}
