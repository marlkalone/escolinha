package com.l2code.escolinha.dto;

import java.time.LocalTime;

public record CreateScheduleRequest(Long classId, Long roomId, Short dayOfWeek,
                                    LocalTime startTime, LocalTime endTime) {}
