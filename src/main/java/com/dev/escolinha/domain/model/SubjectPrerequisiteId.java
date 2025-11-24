package com.dev.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SubjectPrerequisiteId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "prerequisite_subject_id")
    private Long prerequisiteSubjectId;
}

