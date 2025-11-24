package com.dev.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = {"building_id","name"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
}
