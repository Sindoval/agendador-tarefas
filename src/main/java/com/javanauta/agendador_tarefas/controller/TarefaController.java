package com.javanauta.agendador_tarefas.controller;

import com.javanauta.agendador_tarefas.business.TarefaService;
import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefaController {
  private final TarefaService tarefaService;

  @PostMapping
  public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO tarefaDTO,
  @RequestHeader("Authorization") String token) {
    System.out.println("Passou na Controller");
    return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
  }


}
