--CREATE DATABASE predios_db--

CREATE TABLE Setor(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Funcionario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NUL UNIQUE,

    setor_id INT NOT NULL,
    FOREIGN KEY (setor_id) REFERENCES Setor(id) ON DELETE RESTRICT
);

CREATE TABLE RegistroPonto(
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    tipo SMALLINT NOT NULL CHECK (tipo IN (1, 2)),

    funcionario_id INT NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE
);