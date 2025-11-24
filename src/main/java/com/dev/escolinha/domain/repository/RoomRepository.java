package com.dev.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.escolinha.domain.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {}

