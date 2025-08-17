package com.challenger.forumhub.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosUsuario(
        @NotNull
        Long idUsuario,
        String nome,
        String senha) {
}
