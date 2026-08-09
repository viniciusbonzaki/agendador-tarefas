package com.javanauta.agendadortarefas.business.mapper;

import com.javanauta.agendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    Tarefa converterParaTarefa(TarefaDTO tarefaDTO);
    TarefaDTO converterParaTarefaDTO(Tarefa tarefa);
}
