package com.challenger.forumhub.topico.modelo;

import com.challenger.forumhub.repositorio.UsuarioRepository;
import com.challenger.forumhub.topico.Cursos;
import com.challenger.forumhub.topico.StatusTopico;
import com.challenger.forumhub.topico.dto.DadosCriarTopico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Cursos curso;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    @ManyToOne
    private Usuario autor;


    public Topico (DadosCriarTopico dados, Usuario user) {

        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = user;
        this.curso = dados.curso();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTopico.PENDENTE;

    }

}
