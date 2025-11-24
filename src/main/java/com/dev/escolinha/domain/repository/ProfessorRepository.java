package com.dev.escolinha.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.escolinha.domain.model.Professor;
import com.dev.escolinha.domain.repository.projection.ProfessorHoursProjection;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query(value = """
        SELECT p.id AS professor_id,
               p.name AS name,
               COALESCE(SUM(EXTRACT(EPOCH FROM (cs.end_time - cs.start_time)) / 3600.0), 0) AS hours_per_week
        FROM professor p
        LEFT JOIN class c ON c.professor_id = p.id
        LEFT JOIN class_schedule cs ON cs.class_id = c.id
        GROUP BY p.id, p.name
        ORDER BY hours_per_week DESC
        """, nativeQuery = true)
    List<ProfessorHoursProjection> findHoursPerProfessor();
}