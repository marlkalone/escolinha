package com.dev.escolinha.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.escolinha.domain.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {}

