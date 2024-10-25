CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE records (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    project_id INT REFERENCES projects(id),
    hours_worked DECIMAL(5, 2) NOT NULL,
    work_date DATE NOT NULL
);