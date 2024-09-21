-- Script para criar as tabelas no banco de dados

-- 1. Criar a tabela de usuários
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL, -- Identificador único
    nome_usuario VARCHAR(35) NOT NULL, -- Nome do usuário
    documento_usuario VARCHAR(20) NOT NULL UNIQUE, -- Documento único (CPF, RG etc.)
    telefone_usuario VARCHAR(11) NOT NULL,
    email_usuario VARCHAR(50) NOT NULL UNIQUE, -- Email único
    senha_usuario VARCHAR(16) NOT NULL,   -- Senha do usuário
    PRIMARY KEY (id_usuario)
);