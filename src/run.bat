@echo off
rem Criar diretório bin se não existir
if not exist bin (
    mkdir bin
)

rem Compilar os arquivos Java
javac -d bin -cp "lib/*" config\ConnectionFactory.java dao\UsuarioDAO.java models\Usuario.java controllers\UsuarioControle.java Main.java

rem Executar o programa principal
java -cp "lib/*;bin" Main

pause
