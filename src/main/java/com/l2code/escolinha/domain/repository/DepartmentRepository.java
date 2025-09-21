package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
