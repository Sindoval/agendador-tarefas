package com.javanauta.agendador_tarefas.controller;

import com.javanauta.agendador_tarefas.business.TarefaService;
import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefaController {
  private final TarefaService tarefaService;

  @PostMapping
  public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO tarefaDTO,
  @RequestHeader("Authorization") String token) {
    return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
  }

  @GetMapping("/eventos")
  public ResponseEntity<List<TarefaDTO>> buscarListaDeTarefasPorPeriodo(
    @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME)LocalDateTime dataInicial,
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME)LocalDateTime dataFinal
      ) {
    return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
  }

  @GetMapping
  public ResponseEntity<List<TarefaDTO>> buscarTarefasPoremail(@RequestHeader("Authorization") String token) {
    return ResponseEntity.ok(
        tarefaService.buscaTarefaPorEmail(token)
    );
  }
}
