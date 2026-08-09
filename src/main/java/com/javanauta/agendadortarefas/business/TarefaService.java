package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.agendadortarefas.business.mapper.TarefaConverter;
import com.javanauta.agendadortarefas.infrastructure.entity.Tarefa;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.infrastructure.repository.TarefaRepository;

import com.javanauta.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        // Pegar o Email do Usuário através do Token JWT
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        // Definir a Data de Criação como Data e Hora Atual
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        // Definir o Status de Notificação como Pendente
        tarefaDTO.setStatusNotificacao(StatusNotificacaoEnum.PENDENTE);
        // Definir o Email do Usuário extraído do Token
        tarefaDTO.setEmailUsuario(email);

        // Converter a Tarefa à salvar no Banco para Entity
        Tarefa tarefa = tarefaConverter.converterParaTarefa(tarefaDTO);

        // Salvar a Tarefa no Banco de Dados e retornando DTO
        return tarefaConverter.converterParaTarefaDTO(tarefaRepository.save(tarefa));
    }
}
