package com.javanauta.agendadortarefas.business.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String senha;
    private String email;
}
