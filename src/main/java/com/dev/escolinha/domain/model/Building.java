package com.dev.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "building")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Building {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
}
