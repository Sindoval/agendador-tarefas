package com.javanauta.agendador_tarefas.business;

import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador_tarefas.business.mapper.TarefaConverter;
import com.javanauta.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendador_tarefas.infrastructure.client.UsuarioClient;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.agendador_tarefas.infrastructure.security.JwtUtil;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefasRepository tarefasRepository;
  private final TarefaConverter tarefaConverter;
  private final JwtUtil jwtUtil;
  private final UsuarioClient usuarioClient;
  private final TarefaUpdateConverter tarefaUpdateConverter;

  public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
    String email = jwtUtil.extractUsername(token.substring(7));

    tarefaDTO.setEmailUsuario(email);
    tarefaDTO.setDataCriacao(LocalDateTime.now());
    tarefaDTO.setStatus(StatusNotificacaoEnum.PENDENTE);

    TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);


    return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefaEntity));
  }

  public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInical, LocalDateTime dataFinal) {
    return tarefaConverter.paraListTarefasDTO(
        tarefasRepository.findByDataEventoBetweenAndStatus(dataInical, dataFinal, StatusNotificacaoEnum.PENDENTE)
    );
  }

  public List<TarefaDTO> buscaTarefaPorEmail(String token) {
    String email = jwtUtil.extractUsername(token.substring(7));
    return tarefaConverter.paraListTarefasDTO(
        tarefasRepository.findByEmailUsuario(email));
  }

  public void deletaTarefaPorId(String id) {
    try {
      tarefasRepository.deleteById(id);
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente: " + id,
          e.getCause());
    }
  }

  public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
    try {
      TarefaEntity tarefa = tarefasRepository.findById(id).orElseThrow(
          () -> new ResourceNotFoundException("Tarefa não encontrada " + id)
      );
      tarefa.setStatus(status);
      return tarefaConverter.paraTarefaDTO(
          tarefasRepository.save(tarefa)
      );
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
    }
  }

  public TarefaDTO updateTarefas(TarefaDTO dto, String id) {
    try {
      TarefaEntity tarefa = tarefasRepository.findById(id).orElseThrow(
          () -> new ResourceNotFoundException("Tarefa não encontrada " + id)
      );
      tarefaUpdateConverter.updateTarefas(dto, tarefa);
      return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));

    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
    }
  }
}
