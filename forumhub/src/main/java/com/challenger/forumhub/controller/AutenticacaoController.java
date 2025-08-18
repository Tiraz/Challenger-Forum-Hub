package com.challenger.forumhub.controller;

import com.challenger.forumhub.autenticacao.DadosAutenticacao;
import com.challenger.forumhub.infra.security.DadosTokenJWT;
import com.challenger.forumhub.infra.security.TokenService;
import com.challenger.forumhub.topico.modelo.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.nome(), dados.senha());
        var autentication = manager.authenticate(autenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) autentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
