package com.javanauta.agendadortarefas.infrastructure.security;

import com.javanauta.agendadortarefas.business.dtos.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastructure.client.UsuarioClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    // Injeção de Dependendência para fazer requisições com FeignClient
    @Autowired
    private UsuarioClient usuarioClient;

    // Implementação do método para carregar detalhes do usuário pelo E-mail e Token
    public UserDetails carregarDadosUsuario(String email, String token) {
        // Busca os Dados do Usuário através de "usuarioClient" que faz requisições através do FeignClient
        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return User
                .withUsername(usuarioDTO.getEmail())  // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha())  // Define a senha do usuário
                .build();  // Constrói o objeto UserDetails
    }
}
