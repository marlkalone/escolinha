package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {}
