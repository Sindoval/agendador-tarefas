package com.javanauta.agendador_tarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaDTO {
  private String id;
  private String nomeTarefa;
  private String descricao;
  private LocalDateTime dataCriacao;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime dataEvento;
  private String emailUsuario;
  private LocalDateTime dataAlteracao;
  private StatusNotificacaoEnum status;
}
