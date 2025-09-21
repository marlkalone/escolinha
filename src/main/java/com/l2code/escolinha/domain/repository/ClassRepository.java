package com.l2code.escolinha.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.l2code.escolinha.domain.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {}

