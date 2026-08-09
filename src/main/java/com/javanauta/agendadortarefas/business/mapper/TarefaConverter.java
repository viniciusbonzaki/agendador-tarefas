package com.javanauta.agendadortarefas.business.mapper;

import com.javanauta.agendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    Tarefa converterParaTarefa(TarefaDTO tarefaDTO);
    TarefaDTO converterParaTarefaDTO(Tarefa tarefa);

    List<Tarefa> converterParaListaTarefa(List<TarefaDTO> listaTarefaDTO);
    List<TarefaDTO> converterParaListaTarefaDTO(List<Tarefa> listaTarefa);
}
