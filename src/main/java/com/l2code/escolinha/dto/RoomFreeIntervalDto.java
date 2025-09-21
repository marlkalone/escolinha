package com.l2code.escolinha.dto;

import java.time.LocalTime;

public record RoomFreeIntervalDto(Long roomId, LocalTime freeFrom, LocalTime freeTo) {}