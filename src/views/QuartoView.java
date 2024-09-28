package views;

import models.Quarto;
import dao.QuartoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuartoView {
    public static void gerenciarQuartos(){
        Scanner sc = new Scanner(System.in);
        QuartoDAO quartoDAO = new QuartoDAO();
        int opcao;
        boolean run = true;
        

        while (run) {
            System.out.println("\nGerenciamento de Quartos\n");
            System.out.println("1. Criar quarto\n");
            System.out.println("2. Visualizar quarto");
            System.out.println("3. Atualizar quarto");
            System.out.println("4. Remover quarto");
            System.out.println("5. Listar todos os quartos");
            System.out.println("6. Voltar ao menu principal");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    quartoDAO.criarQuarto(sc, quartoDAO);
                    break;  
                case 2:
                    quartoDAO.visualizarQuarto(sc, quartoDAO);
                    break;
                case 3:
                    quartoDAO.atualizarQuarto(sc, quartoDAO);
                    break;
                case 4:
                    quartoDAO.removerQuarto(sc, quartoDAO);
                    break;
                case 5:
                    quartoDAO.listarQuartos(quartoDAO);
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
    }

    private static void criarQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Número do Quarto: ");
        int numero = sc.nextInt();
        System.out.println("Tipo do Quarto: ");
        String tipo = sc.nextLine();
        System.out.println("Preço do Quarto: ");
        double preco = sc.nextDouble();
        System.out.println("Status do Quarto(true para disponivel, false para ocupado): ");
        boolean status = sc.nextBoolean();
        System.out.println("\nCriando um novo quarto...");

        quartoDAO = new Quarto(numero, tipo, preco, status);

        try{
            quartoDAO.criarQuarto(quartoDAO);
            System.out.println("Quarto criado com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar o quarto: " + e.getMessage());
        }
    }

    private static void visualizarQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Informe o número do quarto a ser visualizado: ");
        int numero = sc.nextInt();

        try{
            quartoDAO.visualizarQuarto(numero);
        } catch(SQLException e){
            System.out.println("Erro ao visualizar o quarto: " + e.getMessage());
        }
    }

    private static void atualizarQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Informe o número do quarto a ser atualizado: ");
        int numero = sc.nextInt();

        try{
            Quarto quartoExistente = quartoDAO.visualizarQuarto(numero);
            if (quartoExistente == null) {
                System.out.println("Quarto não encontrado!");
                return;
            }

            System.out.println("Novo Tipo do Quarto: ");
            String tipo = sc.nextLine();
            System.out.println("Novo Preço do Quarto: ");
            double preco = sc.nextDouble();
            System.out.println("Novo Status do Quarto(true para disponível, false para ocupado): ");
            boolean status = sc.nextBoolean();
            System.out.println("\nAtualizando um Quarto...");

            Quarto quartoAtualizado = new Quarto(numero, tipo, preco, status);
            quartoDAO.atualizarQuarto(quartoAtualizado);
            System.out.println("Quarto atualizado com sucesso!");

        } catch(SQLException e){
            System.out.println("Erro ao atualizar o quarto: " + e.getMessage());
        }
    }

    private static void removerQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Informe o número do quarto a ser removido: ");
        int numero = sc.nextInt();

        try{
            quartoDAO.removerQuarto(numero);
            System.out.println("Quarto removido com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao remover o quarto: " + e.getMessage());
        }
    }

    private static void listarQuarto(QuartoDAO quartoDAO){
        System.out.println("\nListando todos os Quartos...");
        try{
            List<Quarto> quartos = quartoDAO.listarQuartos();
            if (quartos.isEmpty()) {
                System.out.println("Nenhum quarto encontrado!");
            } else{
                for (Quarto quarto : quartos) {
                    System.out.println(quarto);
                }
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar os quartos: " + e.getMessage());
        }
    }
}