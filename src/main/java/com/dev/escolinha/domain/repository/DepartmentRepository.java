package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
