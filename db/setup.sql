-- Tabela Funcionario
CREATE TABLE funcionario (
	nome VARCHAR(30) not null,
	documento CHAR(14) not null,
	telefone CHAR(15) not null,
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

-- Tabela Hospede
CREATE TABLE hospede (
	nome VARCHAR(30) not null,
	documento CHAR(14) not null,
	telefone CHAR(15) not null,
	email VARCHAR(50) not null,
	senha VARCHAR(10) not null,
	alimentoRestrito VARCHAR(20) not null,
	PRIMARY KEY (documento)
);

-- Tabela Reserva
CREATE TABLE reserva (
    numeroReserva INT NOT NULL,
    dataEntrada DATE NOT NULL,
    dataSaida DATE NOT NULL,
    documentoFuncionario CHAR(14) NOT NULL,
    numeroQuarto INT NOT NULL,
    documentoHospede CHAR(14) NOT NULL,
    PRIMARY KEY (numeroReserva),
    FOREIGN KEY (documentoFuncionario) REFERENCES funcionario,
    FOREIGN KEY (numeroQuarto) REFERENCES quarto,
    FOREIGN KEY (documentoHospede) REFERENCES hospede
);
