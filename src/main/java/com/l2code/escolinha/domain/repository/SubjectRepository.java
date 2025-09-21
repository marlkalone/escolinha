package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
