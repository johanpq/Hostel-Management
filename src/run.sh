#!/bin/bash

# Criar diretório bin se não existir
mkdir -p ../bin

# Compilar os arquivos Java
javac -d ../bin -cp "lib/*" ../src/config/ConnectionFactory.java ../src/dao/UsuarioDAO.java ../src/models/Usuario.java ../src/controllers/UsuarioControle.java ../src/Main.java

# Executar o programa principal
java -cp "lib/*:../bin" Main
