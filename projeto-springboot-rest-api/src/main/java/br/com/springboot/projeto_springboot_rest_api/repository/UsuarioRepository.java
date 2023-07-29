package br.com.springboot.projeto_springboot_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.projeto_springboot_rest_api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
