@echo off
rem Criar diretório bin se não existir
if not exist bin (
    mkdir bin
)

rem Compilar os arquivos Java
javac -d bin -cp "lib/*" src/config/ConnectionFactory.java src/dao/UsuarioDAO.java src/models/Usuario.java src/controllers/UsuarioControle.java src/Main.java

rem Executar o programa principal
java -cp "lib/*;bin" Main

pause
