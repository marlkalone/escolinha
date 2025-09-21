-- V2__insert_initial_data.sql

INSERT INTO department (name) VALUES ('Exatas'), ('Humanas'), ('Biológicas');
INSERT INTO title (name) VALUES ('Especialista'), ('Mestre'), ('Doutor');
INSERT INTO building (name) VALUES ('Bloco A'), ('Bloco B');

-- Rooms
INSERT INTO room (name, building_id) VALUES
('A-101', 1), ('A-102', 1), ('B-201', 2);

-- Professors
INSERT INTO professor (name, department_id, title_id) VALUES
('Professor Barriga', 1, 3),
('Professora Florinda', 2, 2),
('Professor Madruga', null, null);

-- Subjects
INSERT INTO subject (code, name) VALUES
('ECN-01', 'Economia de Vila'),
('CALC-01', 'Cálculo de alugueis'),
('HIST-01', 'História do Barril');

-- Definindo pré-requisito: para fazer Cálculo de alugueis (id=2), precisa de Economia de Vila (id=1)
INSERT INTO subject_prerequisite (subject_id, prerequisite_subject_id) VALUES (2, 1);

-- Populando turmas para 2025, 2º semestre
-- Turma de Economia de Vila com Professor Madruga
INSERT INTO class (subject_id, professor_id, year, semester, code) VALUES (1, 3, 2025, 2, 'CALC-01-2025-2');
-- Turma de História do Barril com Professora Florinda
INSERT INTO class (subject_id, professor_id, year, semester, code) VALUES (3, 2, 2025, 2, 'HIST-01-2025-2');

-- Agendando as aulas
-- Aula de Cálculo I na sala A-101 (id=1), na Segunda (day=1), das 08h às 10h
INSERT INTO class_schedule (class_id, room_id, day_of_week, start_time, end_time) VALUES (1, 1, 1, '08:00:00', '10:00:00');
-- Aula de Cálculo I na sala A-101 (id=1), na Quarta (day=3), das 08h às 10h
INSERT INTO class_schedule (class_id, room_id, day_of_week, start_time, end_time) VALUES (1, 1, 3, '08:00:00', '10:00:00');

-- Aula de História na sala B-201 (id=3), na Terça (day=2), das 14h às 16h
INSERT INTO class_schedule (class_id, room_id, day_of_week, start_time, end_time) VALUES (2, 3, 2, '14:00:00', '16:00:00');