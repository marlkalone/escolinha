package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {}

