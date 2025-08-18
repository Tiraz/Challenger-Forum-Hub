package com.challenger.forumhub.controller;


import com.challenger.forumhub.repositorio.TopicoRepository;
import com.challenger.forumhub.repositorio.UsuarioRepository;
import com.challenger.forumhub.topico.dto.DadosAtualizarTopico;
import com.challenger.forumhub.topico.dto.DadosCriarTopico;
import com.challenger.forumhub.topico.dto.DadosDetalhadosTopico;
import com.challenger.forumhub.topico.dto.DadosListagemTopicos;
import com.challenger.forumhub.topico.modelo.Topico;
import com.challenger.forumhub.topico.modelo.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<DadosListagemTopicos> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        // Recupera a página de Topicos do banco
        Page<Topico> pageTopicos = topicoRepository.findAll(paginacao);

        // Converte Page<Topico> para Page<DadosListagemTopicos> e retorna só o conteúdo
        return pageTopicos.map(DadosListagemTopicos::new).getContent();
    }

    @GetMapping("/{id}")
    public DadosDetalhadosTopico detalhar(@PathVariable Long id) {

        DadosDetalhadosTopico topico;
        topico = new DadosDetalhadosTopico(topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico não encontrado")));
        return topico;
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarTopico dados) {
        var topico = topicoRepository.getReferenceById(dados.id());
        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (topico.getAutor().getId() == autor.getId()) {
            topico.atualizarInformacoes(dados);
        } else {
            new RuntimeException("Você não pode atulizar um topico que não é seu.");
        }

    }

    @PostMapping
    @Transactional
    public void criarTopico(@RequestBody @Valid DadosCriarTopico dados) {

        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var topico = new Topico(dados, autor);
        topicoRepository.save(topico);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        var autorLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (autorLogado.getId() == topico.getAutor().getId()) {
            // Remove da lista do usuário para quebrar o relacionamento
            Usuario autor = topico.getAutor();
            if (autor != null) {
                autor.getMeusTopicos().remove(topico);
            }

            topicoRepository.delete(topico);
            topicoRepository.flush(); // força execução imediata no banco

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }




}
