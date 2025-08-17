package com.challenger.forumhub.repositorio;

import com.challenger.forumhub.topico.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
