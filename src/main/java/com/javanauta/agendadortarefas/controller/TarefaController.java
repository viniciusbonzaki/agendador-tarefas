package com.javanauta.agendadortarefas.controller;

import com.javanauta.agendadortarefas.business.TarefaService;
import com.javanauta.agendadortarefas.business.dtos.TarefaDTORecord;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTORecord> salvarTarefa(@RequestHeader("Authorization") String token,
                                                        @RequestBody TarefaDTORecord tarefaDTO) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTORecord>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTORecord>> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        List<TarefaDTORecord> listaTarefas = tarefaService.buscarTarefasPorEmail(token);
        return ResponseEntity.ok(listaTarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletarTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTORecord> atualizarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alterarStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTORecord> atualizarTarefa(@RequestBody TarefaDTORecord tarefaDTO,
                                                           @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alterarTarefa(tarefaDTO, id));
    }
}
