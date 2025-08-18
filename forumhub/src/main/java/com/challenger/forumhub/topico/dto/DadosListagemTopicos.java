package com.challenger.forumhub.topico.dto;

import com.challenger.forumhub.topico.Cursos;
import com.challenger.forumhub.topico.StatusTopico;
import com.challenger.forumhub.topico.modelo.Topico;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String mensagem,
        Cursos curso,
        StatusTopico status,
        String nome
) {
    public DadosListagemTopicos(Topico d) {
         this(d.getId(), d.getTitulo(), d.getMensagem(), d.getCurso(), d.getStatus(), d.getAutor().getNome());
    }
}
