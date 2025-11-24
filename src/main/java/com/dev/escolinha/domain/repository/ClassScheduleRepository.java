package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.escolinha.domain.model.ClassSchedule;

import java.time.LocalTime;
import java.util.List;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    @Query("SELECT cs FROM ClassSchedule cs " +
           "WHERE cs.room.id = :roomId AND cs.dayOfWeek = :dayOfWeek " +
           "AND NOT (cs.endTime <= :startTime OR cs.startTime >= :endTime)")
    List<ClassSchedule> findOverlappingInRoom(@Param("roomId") Long roomId,
                                              @Param("dayOfWeek") Short dayOfWeek,
                                              @Param("startTime") LocalTime startTime,
                                              @Param("endTime") LocalTime endTime);

    @Query("SELECT cs FROM ClassSchedule cs " + 
           "WHERE cs.room.id = :roomId AND cs.dayOfWeek = :dayOfWeek " + 
           "ORDER BY cs.startTime ASC")
    List<ClassSchedule> findByRoomIdAndDayOfWeekOrderByStartTime(@Param("roomId") Long roomId, @Param("dayOfWeek") Short dayOfWeek);

    @Query("SELECT cs FROM ClassSchedule cs " +
           "WHERE cs.dayOfWeek = :dayOfWeek " +
           "ORDER BY cs.room.id ASC, cs.startTime ASC")
    List<ClassSchedule> findByDayOfWeekOrderByRoom_IdAscStartTimeAsc(@Param("dayOfWeek") Short dayOfWeek);
}

