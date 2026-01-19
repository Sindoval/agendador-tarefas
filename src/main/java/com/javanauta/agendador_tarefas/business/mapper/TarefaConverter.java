package com.javanauta.agendador_tarefas.business.mapper;

import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

  TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

  TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
}
