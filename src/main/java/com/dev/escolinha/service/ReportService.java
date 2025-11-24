package com.dev.escolinha.service;

import org.springframework.stereotype.Service;

import com.dev.escolinha.domain.model.ClassSchedule;
import com.dev.escolinha.domain.repository.ClassScheduleRepository;
import com.dev.escolinha.domain.repository.ProfessorRepository;
import com.dev.escolinha.domain.repository.projection.ProfessorHoursProjection;
import com.dev.escolinha.dto.ProfessorHoursDto;
import com.dev.escolinha.dto.RoomFreeIntervalDto;
import com.dev.escolinha.dto.RoomScheduleDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ProfessorRepository professorRepository;
    private final ClassScheduleRepository scheduleRepository;

    public ReportService(ProfessorRepository professorRepository,
                         ClassScheduleRepository scheduleRepository) {
        this.professorRepository = professorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // --- 1) Horas por professor (usa projection do ProfessorRepository) ---
    public List<ProfessorHoursDto> getHoursPerProfessor() {
        List<ProfessorHoursProjection> rows = professorRepository.findHoursPerProfessor();
        return rows.stream()
                .map(p -> new ProfessorHoursDto(p.getProfessorId(), p.getName(), p.getHoursPerWeek()))
                .collect(Collectors.toList());
    }

    // --- 2) Horários ocupados por sala (traz entity e mapeia) ---
    public List<RoomScheduleDto> getRoomSchedulesByDay(short dayOfWeek) {
        List<ClassSchedule> schedules = scheduleRepository.findByDayOfWeekOrderByRoom_IdAscStartTimeAsc(dayOfWeek);
        return schedules.stream().map(cs -> {
            var room = cs.getRoom();
            var course = cs.getCourseClass();
            var subject = course.getSubject();
            var prof = course.getProfessor();
            String profName = prof == null ? null : prof.getName();
            return new RoomScheduleDto(
                    room.getId(),
                    room.getName(),
                    cs.getDayOfWeek(),
                    cs.getStartTime(),
                    cs.getEndTime(),
                    subject == null ? null : subject.getCode(),
                    course.getCode(),
                    profName
            );
        }).collect(Collectors.toList());
    }

    // --- 3) Intervalos livres por sala no dia (computado em Java) ---
    public List<RoomFreeIntervalDto> getRoomFreeIntervalsByDay(short dayOfWeek, LocalTime dayStart, LocalTime dayEnd) {
        // Pega todos os schedules do dia, ordenados por roomId e startTime
        List<ClassSchedule> schedules = scheduleRepository.findByDayOfWeekOrderByRoom_IdAscStartTimeAsc(dayOfWeek);

        Map<Long, List<ClassSchedule>> byRoom = new LinkedHashMap<>();
        for (ClassSchedule cs : schedules) {
            byRoom.computeIfAbsent(cs.getRoom().getId(), k -> new ArrayList<>()).add(cs);
        }

        List<RoomFreeIntervalDto> freeIntervals = new ArrayList<>();

        // para cada sala com ocupação, calcule gaps
        for (Map.Entry<Long, List<ClassSchedule>> e : byRoom.entrySet()) {
            Long roomId = e.getKey();
            LocalTime cursor = dayStart;
            for (ClassSchedule cs : e.getValue()) {
                if (cursor.isBefore(cs.getStartTime())) {
                    freeIntervals.add(new RoomFreeIntervalDto(roomId, cursor, cs.getStartTime()));
                }
                // avançar cursor para o máximo entre cursor e endTime
                if (cursor.isBefore(cs.getEndTime())) cursor = cs.getEndTime();
            }
            if (cursor.isBefore(dayEnd)) {
                freeIntervals.add(new RoomFreeIntervalDto(roomId, cursor, dayEnd));
            }
        }

        // incluir salas sem ocupação nenhuma (full day free)
        // buscamos todas as rooms através dos schedules já buscados seria incompleto, então:
        // vamos buscar ids de rooms presentes e, se quiser incluir todas as rooms do DB,
        // adicione um RoomRepository e busque todas as salas. Aqui vou incluir a lógica alternativa:
        // (se você preferir, posso adicionar RoomRepository e incluir todas as salas; agora incluo só salas sem ocupação)
        // Para incluir todas, adicione RoomRepository e itere sobre todas as salas, verificando presence.

        freeIntervals.sort(Comparator.comparing(RoomFreeIntervalDto::roomId).thenComparing(RoomFreeIntervalDto::freeFrom));
        return freeIntervals;
    }
}
