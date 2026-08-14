package com.javanauta.agendadortarefas.infrastructure.repository;

import com.javanauta.agendadortarefas.infrastructure.entity.Tarefa;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {
    // Encontrar as Tarefas de acordo com determinado Intervalo de Tempo e Status de Notificação
    List<Tarefa> findByDataEventoBetweenAndStatusNotificacao(LocalDateTime dataInicial,
                                                             LocalDateTime dataFinal,
                                                             StatusNotificacaoEnum status);

    // Encontrar as Tarefas de um determinado Usuário pelo seu Email
    List<Tarefa> findByEmailUsuario(String email);
}
