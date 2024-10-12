import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import config.ConnectionFactory;
import dao.HospedeDAO;
import views.FuncionarioView;
import views.HospedeView;
import views.QuartoView;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continuar = true;
        HospedeDAO hospedeDao = new HospedeDAO();
        
        while(continuar) {
            try {
                boolean loginDeuCerto = false;
                System.out.println("****SELECIONE****");
                System.out.println("1. Funcionário");
                System.out.println("2. Hospede");
                System.out.println("3. Sair");
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
        
                        loginDeuCerto = checaFuncionario(input, email, senha);

                        if(loginDeuCerto) {
                            mostrarMenu(input);
                        }

                        System.out.println("-------------------------");

                        break;
                    case 2:
                        loginDeuCerto = false;
                        System.out.println("-------------------------");
                        System.out.println("1. Entrar");
                        System.out.println("2. Cadastrar");
                        System.out.println("-------------------------");
                        int escolha = input.nextInt();

                        switch(escolha) {
                            case 1:
                                input.nextLine();
                                System.out.println("----------LOGIN----------");
                    
                                System.out.print("Email: ");
                                String emailHospede = input.nextLine();
                
                                System.out.print("Senha: ");
                                String senhaHospede = input.nextLine();
                
                                loginDeuCerto = checaHospede(input, emailHospede, senhaHospede);
        
                                if(loginDeuCerto) {
                                    HospedeView.gerenciarHospede();
                                }
        
                                System.out.println("-------------------------");

                                break;

                            case 2:
                                input.nextLine();
                                HospedeView.criarHospede(input, hospedeDao);
                                break;
                            default :
                                System.out.println("Opção inválida! ");
                        }

                        break;
                    case 3:
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opção Inválida!");
                } 
 
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Tente novamente.");
                input.nextLine(); 
            } catch (Exception e) {
                System.out.println("Ocorreu um erro! Tente novamente.");
                e.printStackTrace();
            }
                
        };
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
                        HospedeView.gerenciarHospedeFuncionario();
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

    public static boolean checaFuncionario(Scanner input, String email, String senha) {
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

    public static boolean checaHospede(Scanner input, String email, String senha) {
        boolean login = false;
        String sql = "SELECT * FROM hospede WHERE email = ? AND senha = ?";

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
