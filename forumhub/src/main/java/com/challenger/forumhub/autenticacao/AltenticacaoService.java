package com.challenger.forumhub.autenticacao;


import com.challenger.forumhub.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AltenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositorio.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

}