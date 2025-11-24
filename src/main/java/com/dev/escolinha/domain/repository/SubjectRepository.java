package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
