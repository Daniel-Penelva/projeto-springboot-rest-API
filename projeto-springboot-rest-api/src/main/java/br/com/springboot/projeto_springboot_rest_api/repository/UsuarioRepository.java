package br.com.springboot.projeto_springboot_rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.projeto_springboot_rest_api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
	/**
	 * O script abaixo usa a anotação @Query para definir uma consulta personalizada no banco de dados. A consulta é definida usando a 
	 * linguagem JPQL (Java Persistence Query Language) e será usada para buscar usuários no banco de dados com base no nome informado 
	 * como parâmetro.
	 * 
	 * Vamos explicar cada parte do script:
	 * 
	 * @Query(value = "select u from Usuario u where u.nome like %?1%"): Essa é a anotação @Query que indica que a consulta personalizada 
	 * será executada no banco de dados. O atributo value é usado para definir a consulta JPQL. Neste caso, a consulta está buscando todos 
	 * os registros da entidade Usuario (representada pela variável "u") onde o nome do usuário (u.nome) contém o valor do primeiro 
	 * parâmetro (?1) usando o operador LIKE. O % antes e depois do parâmetro indica que o valor do nome pode aparecer em qualquer posição. 
	 * O uso de upper e trim é para garantir que a consulta seja insensível a maiúsculas/minúsculas e também remove quaisquer espaços em 
	 * branco no início e no final do valor do parâmetro ?1.
	 *  
	 *  List<Usuario> buscarByName(String nome): Esse é o método que está sendo definido no repositório (ou serviço) e que será usado para 
	 *  executar a consulta personalizada. O método recebe um parâmetro nome que será usado na consulta para buscar usuários cujo nome 
	 *  contenha o valor informado.
	 *  
	 *  Quando esse método é chamado, a consulta JPQL definida na anotação @Query será executada no banco de dados, filtrando os registros 
	 *  com base no nome fornecido como parâmetro. O resultado da consulta será uma lista de objetos Usuario que correspondem aos critérios 
	 *  da consulta.
	 *  
	 *  Em resumo, esse método buscarByName() é uma consulta personalizada que busca usuários no banco de dados cujo nome contenha o valor 
	 *  fornecido como parâmetro. Isso permite buscar usuários com nomes parcialmente correspondentes, facilitando a pesquisa por nome no 
	 *  banco de dados.
	 * */
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarByName(String nome);
	
}
