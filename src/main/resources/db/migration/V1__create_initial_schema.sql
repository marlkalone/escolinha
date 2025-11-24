-- V1__create_initial_schema.sql

-- Tabela de Title
CREATE TABLE title (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de Departament
CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de Professors
CREATE TABLE professor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id BIGINT,
    title_id BIGINT,
    CONSTRAINT fk_professor_department FOREIGN KEY (department_id) REFERENCES department (id),
    CONSTRAINT fk_professor_title FOREIGN KEY (title_id) REFERENCES title (id)
);

-- Tabela de Subjects
CREATE TABLE subject (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    parent_subject_id BIGINT,
    CONSTRAINT fk_subject_parent FOREIGN KEY (parent_subject_id) REFERENCES subject (id)
);

-- Tabela de Prerequisitos
CREATE TABLE subject_prerequisite (
    subject_id BIGINT NOT NULL,
    prerequisite_subject_id BIGINT NOT NULL,
    PRIMARY KEY (subject_id, prerequisite_subject_id),
    CONSTRAINT fk_prerequisite_subject FOREIGN KEY (subject_id) REFERENCES subject (id),
    CONSTRAINT fk_prerequisite_prereq FOREIGN KEY (prerequisite_subject_id) REFERENCES subject (id)
);

-- Tabela de Buildings
CREATE TABLE building (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de Rooms
CREATE TABLE room (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    building_id BIGINT NOT NULL,
    CONSTRAINT fk_room_building FOREIGN KEY (building_id) REFERENCES building (id),
    CONSTRAINT uk_room_building_name UNIQUE (building_id, name)
);

-- Tabela de Classes
CREATE TABLE class (
    id BIGSERIAL PRIMARY KEY,
    subject_id BIGINT NOT NULL,
    professor_id BIGINT,
    year INT,
    semester INT,
    code VARCHAR(255),
    CONSTRAINT fk_class_subject FOREIGN KEY (subject_id) REFERENCES subject (id),
    CONSTRAINT fk_class_professor FOREIGN KEY (professor_id) REFERENCES professor (id)
);

-- Tabela de Class Schedules
CREATE TABLE class_schedule (
    id BIGSERIAL PRIMARY KEY,
    class_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    day_of_week SMALLINT,
    start_time TIME,
    end_time TIME,
    CONSTRAINT fk_schedule_class FOREIGN KEY (class_id) REFERENCES class (id),
    CONSTRAINT fk_schedule_room FOREIGN KEY (room_id) REFERENCES room (id)
);

-- Índice para otimizar buscas por schedules em uma class/day
CREATE INDEX idx_schedule_room_day ON class_schedule (room_id, day_of_week, start_time);