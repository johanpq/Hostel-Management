@echo off
rem Criar diretório bin se não existir
if not exist bin (
    mkdir bin
)

rem Compilar os arquivos Java
javac -d bin -cp "lib/*" config\ConnectionFactory.java models\Usuario.java Main.java models\Funcionario.java models\Hospede.java models\Quarto.java models\Reserva.java  dao\FuncionarioDAO.java dao\HospedeDAO.java dao\QuartoDAO.java dao\ReservaDAO.java views\FuncionarioView.java views\QuartoView.java views\HospedeView.java views\ReservaView.java

rem Executar o programa principal
java -cp "lib/*;bin" Main

pause
