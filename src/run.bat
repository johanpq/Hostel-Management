@echo off
rem Criar diretório bin se não existir
if not exist bin (
    mkdir bin
)

rem Compilar os arquivos Java
javac -d bin -cp "lib/*" config\ConnectionFactory.java models\Usuario.java Main.java

rem Executar o programa principal
java -cp "lib/*;bin" Main

pause
