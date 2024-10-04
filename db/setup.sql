-- Tabela Funcionario
CREATE TABLE funcionario (
	nome VARCHAR(30) not null,
	documento VARCHAR(14) not null,
	telefone VARCHAR(15) not null,
	email VARCHAR(50) not null,
	senha VARCHAR(10) not null,
	cargo VARCHAR(30) not null,
	adminF BOOLEAN not null,
	PRIMARY KEY (documento)
);