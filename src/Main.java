import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import config.ConnectionFactory;

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
                        System.out.println("Admin: 1.Sim | 2.Não");
                        int ehAdmin = input.nextInt();  
                        boolean ehFuncAdmin = (ehAdmin == 1);
    
                        input.nextLine();  
                        System.out.println("----------LOGIN----------");
    
                        System.out.print("Email: ");
                        String email = input.nextLine();
    
                        System.out.print("Senha: ");
                        String senha = input.nextLine();
    
                        checarAdmin(input, email, senha, ehFuncAdmin);
    
                        break;
    
                    case 2:
                        System.out.println("Case 2 hospede");
                        break;
    
                    default:
                        System.out.println("Opção Inválida!");
                }
    
            } catch (Exception e) {
                System.out.println("Ocorreu uma exceção! Tente novamente!");
            } finally {
                input.close();
            }
        } while(!loginDeuCerto);

    }

    public static void mostrarMenu(Scanner input) {
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
                    System.out.println("Case 1");
                    break;
                case 2:
                    System.out.println("Case 2");
                    break;
                case 3:
                    System.out.println("Case 3");
                    break;
                case 4:
                    System.out.println("Case 4");
                    break;
                case 5:
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
    }

    public static void checarAdmin(Scanner input, String email, String senha, boolean ehFuncAdmin) {
        String sql = "SELECT * FROM funcionario WHERE email = ? AND senha = ? AND adminf = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, senha);
            statement.setBoolean(3, ehFuncAdmin);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                mostrarMenu(input);
            } else {
                System.out.println("Email ou senha incorretos!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados ou executar a consulta!");
            e.printStackTrace();
        }
    }
}
