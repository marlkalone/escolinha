package com.l2code.escolinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l2code.escolinha.domain.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {}

