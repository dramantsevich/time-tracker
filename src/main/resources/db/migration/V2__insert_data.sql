
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ROLE_ADMIN'),
('user', 'user123', 'ROLE_USER');

INSERT INTO projects (name) VALUES
('Project Alpha'),
('Project Beta');

INSERT INTO records (user_id, project_id, hours_worked, work_date) VALUES
(1, 1, 8.0, '2024-10-01'),
(2, 2, 6.5, '2024-10-02');
