-- Tabela Funcionario
CREATE TABLE funcionario (
	nome VARCHAR(30) not null,
	documento VARCHAR(14) not null,
	telefone VARCHAR(15) not null,
	email VARCHAR(50) not null,
	senha VARCHAR(10) not null,
	cargo VARCHAR(30) not null,
	PRIMARY KEY (documento)
);

-- Tabela Quarto
CREATE TABLE quarto (
	numero INT not null,
	tipo VARCHAR(20) not null,
	preco FLOAT not null,
	status BOOLEAN not null,
	PRIMARY KEY (numero)
);