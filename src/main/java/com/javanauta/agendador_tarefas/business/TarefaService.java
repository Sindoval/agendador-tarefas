package com.javanauta.agendador_tarefas.business;

import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador_tarefas.business.mapper.TarefaConverter;
import com.javanauta.agendador_tarefas.infrastructure.client.UsuarioClient;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.agendador_tarefas.infrastructure.security.JwtUtil;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefasRepository tarefasRepository;
  private final TarefaConverter tarefaConverter;
  private final JwtUtil jwtUtil;
  private final UsuarioClient usuarioClient;

  public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
    String email = jwtUtil.extractUsername(token.substring(7));

    tarefaDTO.setEmailUsuario(email);
    tarefaDTO.setDataCriacao(LocalDateTime.now());
    tarefaDTO.setStatus(StatusNotificacaoEnum.PENDENTE);

    TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);


    return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefaEntity));
  }

}
