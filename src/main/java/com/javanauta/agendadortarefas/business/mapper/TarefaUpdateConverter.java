package com.javanauta.agendadortarefas.business.mapper;

import com.javanauta.agendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.agendadortarefas.infrastructure.entity.Tarefa;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    // @MappingTarget -> Se algum Campo de TarefaDTO não for passado para alterar, pegar o valor de Tarefa Entity
    void alterarTarefa(TarefaDTO tarefaDTO, @MappingTarget Tarefa tarefa);
}
