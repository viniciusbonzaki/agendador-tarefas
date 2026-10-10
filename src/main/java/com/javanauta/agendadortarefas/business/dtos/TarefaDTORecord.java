package com.javanauta.agendadortarefas.business.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record TarefaDTORecord(String id,
                              String nomeTarefa,
                              String descricao,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataCriacao,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataEvento,
                              String emailUsuario,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataAlteracao,
                              StatusNotificacaoEnum statusNotificacao) {
}
