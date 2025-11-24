package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {}

