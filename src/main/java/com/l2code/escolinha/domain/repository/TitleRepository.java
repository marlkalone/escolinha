package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {}
