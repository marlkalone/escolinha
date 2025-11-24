package com.dev.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject_prerequisite")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SubjectPrerequisite {
    @EmbeddedId
    private SubjectPrerequisiteId id;

    @MapsId("subjectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @MapsId("prerequisiteSubjectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prerequisite_subject_id", nullable = false)
    private Subject prerequisite;
}

