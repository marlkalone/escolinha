package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.SubjectPrerequisite;
import com.dev.escolinha.domain.model.SubjectPrerequisiteId;

public interface SubjectPrerequisiteRepository extends JpaRepository<SubjectPrerequisite, SubjectPrerequisiteId> {}
