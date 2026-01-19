package com.javanauta.agendador_tarefas.infrastructure.security;

import com.javanauta.agendador_tarefas.business.dto.UsuarioDTO;
import com.javanauta.agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email, String token) {
        String tokenCompleto = token.startsWith("Bearer ") ? token : "Bearer " + token;
        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, tokenCompleto);

        return User
            .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
            .password(usuarioDTO.getSenha())
            .build();
    }
}
