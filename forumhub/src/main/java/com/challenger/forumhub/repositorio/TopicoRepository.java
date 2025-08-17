package com.challenger.forumhub.repositorio;

import com.challenger.forumhub.topico.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
