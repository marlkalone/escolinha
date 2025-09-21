package com.l2code.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "title")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Title {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
