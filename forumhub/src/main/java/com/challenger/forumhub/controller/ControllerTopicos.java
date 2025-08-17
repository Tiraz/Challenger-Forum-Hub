package com.challenger.forumhub.controller;


import com.challenger.forumhub.repositorio.TopicoRepository;
import com.challenger.forumhub.repositorio.UsuarioRepository;
import com.challenger.forumhub.topico.dto.DadosCriarTopico;
import com.challenger.forumhub.topico.dto.DadosListagemTopicos;
import com.challenger.forumhub.topico.modelo.Topico;
import com.challenger.forumhub.topico.modelo.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class ControllerTopicos {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping
    public Page<DadosListagemTopicos> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        // Recupera a página de Topicos do banco
        Page<Topico> pageTopicos = topicoRepository.findAll(paginacao);

        // Converte Page<Topico> para Page<DadosListagemTopicos>
        return pageTopicos.map(DadosListagemTopicos::new);
    }
    @PostMapping
    @Transactional
    public void criarTopco(@RequestBody @Valid DadosCriarTopico dados) {

        Usuario autor = usuarioRepository.findById(dados.autor().idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var topico = new Topico(dados, autor);
        topicoRepository.save(topico);
    }


}
