package com.javanauta.agendador_tarefas.controller;

import com.javanauta.agendador_tarefas.business.TarefaService;
import com.javanauta.agendador_tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicial,
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal
  ) {
    return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
  }

  @GetMapping
  public ResponseEntity<List<TarefaDTO>> buscarTarefasPoremail(
      @RequestHeader("Authorization") String token) {
    return ResponseEntity.ok(
        tarefaService.buscaTarefaPorEmail(token)
    );
  }

  @DeleteMapping
  public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
    tarefaService.deletaTarefaPorId(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping
  public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status")
  StatusNotificacaoEnum status, @RequestParam("id") String id) {
    return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
  }

  @PutMapping
  public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO tarefaDTO,
      @RequestParam("id") String id) {
    return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id));
  }
}
