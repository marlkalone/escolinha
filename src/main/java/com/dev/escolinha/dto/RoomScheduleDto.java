package com.dev.escolinha.dto;

import java.time.LocalTime;

public record RoomScheduleDto(
        Long roomId,
        String roomName,
        Short dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        String subjectCode,
        String classCode,
        String professorName
) {}