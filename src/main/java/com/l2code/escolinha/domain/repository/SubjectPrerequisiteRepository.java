package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.SubjectPrerequisite;
import com.l2code.escolinha.domain.model.SubjectPrerequisiteId;

public interface SubjectPrerequisiteRepository extends JpaRepository<SubjectPrerequisite, SubjectPrerequisiteId> {}
