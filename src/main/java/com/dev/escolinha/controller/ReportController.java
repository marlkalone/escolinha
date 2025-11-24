package com.dev.escolinha.controller;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.escolinha.dto.ProfessorHoursDto;
import com.dev.escolinha.dto.RoomFreeIntervalDto;
import com.dev.escolinha.dto.RoomScheduleDto;
import com.dev.escolinha.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Relatórios", description = "Endpoints para gerar relatórios da escolinha") // Agrupa os endpoints
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Relatório de Horas por Professor",
               description = "Retorna a quantidade total de horas semanais alocadas para cada professor.")
    @GetMapping("/hours-per-professor")
    public ResponseEntity<List<ProfessorHoursDto>> hoursPerProfessor() {
        return ResponseEntity.ok(reportService.getHoursPerProfessor());
    }

    @Operation(summary = "Relatório de Horários Ocupados por Sala",
               description = "Retorna a grade de horários ocupados para um determinado dia da semana.")
    @GetMapping("/room-schedules")
    public ResponseEntity<List<RoomScheduleDto>> roomSchedules(
            @Parameter(description = "Dia da semana (1=Segunda, 2=Terça, etc.)", required = true, example = "1")
            @RequestParam
            @Min(value = 1, message = "Dia deve ser no mínimo 1 (Segunda)")
            @Max(value = 7, message = "Dia deve ser no máximo 7 (Domingo)")
            short day) {
        return ResponseEntity.ok(reportService.getRoomSchedulesByDay(day));
    }

    @Operation(summary = "Relatório de Intervalos Livres por Sala",
               description = "Calcula e retorna os intervalos de tempo livres para as salas em um determinado dia e período.")
    @GetMapping("/room-free-intervals")
    public ResponseEntity<List<RoomFreeIntervalDto>> roomFreeIntervals(
            @Parameter(description = "Dia da semana (1=Segunda, etc.)", required = true, example = "1")
            @RequestParam
            @Min(value = 1, message = "Dia deve ser no mínimo 1 (Segunda)")
            @Max(value = 7, message = "Dia deve ser no máximo 7 (Domingo)")
            short day,

            @Parameter(description = "Hora de início do período de análise (formato HH:mm:ss)", required = true, example = "08:00:00")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime dayStart,

            @Parameter(description = "Hora de término do período de análise (formato HH:mm:ss)", required = true, example = "22:00:00")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime dayEnd) {
        return ResponseEntity.ok(reportService.getRoomFreeIntervalsByDay(day, dayStart, dayEnd));
    }
}
