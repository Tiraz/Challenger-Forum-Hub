package com.challenger.forumhub.topico.dto;

import com.challenger.forumhub.topico.Cursos;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosCriarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        Cursos curso,
        DadosUsuario autor
) {
}
