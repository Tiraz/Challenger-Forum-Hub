package com.challenger.forumhub.topico.dto;

import com.challenger.forumhub.topico.Cursos;
import com.challenger.forumhub.topico.StatusTopico;
import com.challenger.forumhub.topico.modelo.Topico;

public record DadosDetalhadosTopico(
        long id,
        String titulo,
        String mensagem,
        String autor,
        Cursos curso,
        StatusTopico status
) {
    public DadosDetalhadosTopico(Topico dados) {
        this(dados.getId(), dados.getTitulo(), dados.getMensagem(), dados.getAutor().getNome(),
                dados.getCurso(), dados.getStatus());
    }
}
