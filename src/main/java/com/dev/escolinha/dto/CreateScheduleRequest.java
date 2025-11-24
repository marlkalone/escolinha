package com.dev.escolinha.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CreateScheduleRequest(
        @NotNull(message = "O ID da turma é obrigatório")
        Long classId,

        @NotNull(message = "O ID da sala é obrigatório")
        Long roomId,

        @NotNull(message = "O dia da semana é obrigatório")
        @Min(value = 1, message = "Dia da semana deve ser entre 1 (Seg) e 7 (Dom)")
        @Max(value = 7, message = "Dia da semana deve ser entre 1 (Seg) e 7 (Dom)")
        Short dayOfWeek,

        @NotNull(message = "Horário de início obrigatório")
        LocalTime startTime,

        @NotNull(message = "Horário de fim obrigatório")
        LocalTime endTime
) {
    public CreateScheduleRequest {
        if (startTime != null && endTime != null && !endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("O horário final deve ser posterior ao inicial");
        }
    }
}
