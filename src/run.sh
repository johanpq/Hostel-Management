#!/bin/bash

# Criar diretório bin se não existir
mkdir -p ../bin

# Compilar os arquivos Java
javac -d ../bin -cp "lib/*" ../src/config/ConnectionFactory.java  ../src/models/Usuario.java ../src/models/Funcionario.java ../src/dao/FuncionarioDAO.java ../src/views/FuncionarioView.java ../src/models/Quarto.java ../src/dao/QuartoDAO.java ../src/views/QuartoView.java ../src/models/Hospede.java ../src/dao/HospedeDAO.java ../src/views/HospedeView.java ../src/models/Reserva.java ../src/dao/ReservaDAO.java ../src/views/ReservaView.java ../src/Main.java

# Executar o programa principal
java -cp "lib/*:../bin" Main
