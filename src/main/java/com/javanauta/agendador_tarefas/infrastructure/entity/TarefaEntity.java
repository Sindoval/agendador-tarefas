package com.javanauta.agendador_tarefas.infrastructure.entity;

import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("tarefas")
public class TarefaEntity {
  @Id
  private String id;
  private String nomeTarefa;
  private String descricao;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataEvento;
  private String emailUsuario;
  private LocalDateTime dataAlteracao;
  private StatusNotificacaoEnum status;
}
