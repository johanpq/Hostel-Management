import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import config.ConnectionFactory;
import views.FuncionarioView;
import views.HospedeView;
import views.QuartoView;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean loginDeuCerto = false;

        do {
            try {
                System.out.println("****SELECIONE****");
                System.out.println("1. Funcionário");
                System.out.println("2. Hospede");
                System.out.print("-> ");
                int funcOuHosp = input.nextInt();
                
                input.nextLine();
    
                switch (funcOuHosp) {
                    case 1:
                        System.out.println("----------LOGIN----------");
        
                        System.out.print("Email: ");
                        String email = input.nextLine();
        
                        System.out.print("Senha: ");
                        String senha = input.nextLine();
        
                        loginDeuCerto = checarAdmin(input, email, senha);

                        if(loginDeuCerto) {
                            mostrarMenu(input);
                        } else {
                            System.err.println("É funcionário mais sem ser Admin");
                        }

                        System.out.println("-------------------------");

                        break;
                    case 2:
                        System.out.println("Case 2 hospede");
                        break;
    
                    default:
                        System.out.println("Opção Inválida!");
                } 
                break;

            } catch (Exception e) {
                System.out.println("Ocorreu uma exceção! Tente novamente!");
            } 
                
        } while(!loginDeuCerto);
        input.close();
    }

    public static void mostrarMenu(Scanner input) {
        boolean sair = false;
        do {
            try {
                System.out.println("---------MENU----------");
                System.out.println(" 1. Hospede");
                System.out.println(" 2. Funcionario");
                System.out.println(" 3. Quarto");
                System.out.println(" 4. Reserva");
                System.out.println(" 5. Sair");
                System.out.println("-----------------------");
                System.out.print("-> ");
                int escolha = input.nextInt();
    
                switch (escolha) {
                    case 1:
                        HospedeView.gerenciarHospede();
                        break;
                    case 2:
                        FuncionarioView.gerenciarFuncionarios();
                        break;
                    case 3:
                        QuartoView.gerenciarQuartos();
                        break;
                    case 4:
                        System.out.println("Case 4");
                        break;
                    case 5:
                        sair = true;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Valor inválido!");
            } finally {
                input.nextLine(); // Limpar o buffer
            }
        } while(!sair);
    }

    public static boolean checarAdmin(Scanner input, String email, String senha) {
        boolean login = false;
        String sql = "SELECT * FROM funcionario WHERE email = ? AND senha = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, senha);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                login = true;
            } else {
                login = false;
                System.out.println("Email ou senha incorretos!");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados ou executar a consulta!");
            e.printStackTrace();
        }
        return login;
    }
}
