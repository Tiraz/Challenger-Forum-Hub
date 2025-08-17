package com.challenger.forumhub.topico.dto;

import com.challenger.forumhub.topico.Cursos;
import com.challenger.forumhub.topico.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        Cursos curso,
        StatusTopico status
) {
}
