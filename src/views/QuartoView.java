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
        boolean run = true;

        while (run) {
            System.out.println("\nGerenciamento de Quartos\n");
            System.out.println("1. Criar quarto");
            System.out.println("2. Visualizar quarto");
            System.out.println("3. Atualizar quarto");
            System.out.println("4. Remover quarto");
            System.out.println("5. Listar todos os quartos");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("-> ");
            
            String opcaoStr = sc.nextLine();
            int opcao;
            try {
                opcao = Integer.parseInt(opcaoStr);  
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;  // Se der erro, volta ao começo do loop
            }

            switch (opcao) {
                case 1:
                    criarQuarto(sc, quartoDAO);
                    break;  
                case 2:
                    visualizarQuarto(sc, quartoDAO);
                    break;
                case 3:
                    atualizarQuarto(sc, quartoDAO);
                    break;
                case 4:
                    removerQuarto(sc, quartoDAO);
                    break;
                case 5:
                    listarQuartos(quartoDAO);
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
    }

    private static void criarQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Número do Quarto: ");
        int numero = sc.nextInt();
        sc.nextLine();
        System.out.println("Tipo do Quarto: ");
        String tipo = sc.nextLine();
        System.out.println("Preço do Quarto: ");
        double preco = sc.nextDouble();
        System.out.println("Status do Quarto(true para disponivel, false para ocupado): ");
        boolean status = sc.nextBoolean();
        System.out.println("\nCriando um novo quarto...");

        Quarto quarto = new Quarto(numero, tipo, preco, status);

        try{
            quartoDAO.criarQuarto(quarto);
            System.out.println("Quarto criado com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar o quarto: " + e.getMessage());
        }
    }

    public static void visualizarQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Informe o número do quarto a ser visualizado: ");
        int numero = sc.nextInt();

        try{
            Quarto quarto = quartoDAO.visualizarQuarto(numero);
            if (quarto == null) {
                System.out.println("\nNenhum quarto encontrado com o número informado!");
            } else{
                System.out.println(quarto);
            }
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

            sc.nextLine();
            System.out.println("Novo Tipo do Quarto: ");
            String tipo = sc.nextLine();
            System.out.println("Novo Preço do Quarto: ");
            double preco = sc.nextDouble();
            System.out.println("Novo Status do Quarto(true para disponível, false para ocupado): ");
            boolean status = sc.nextBoolean();
            System.out.println("\nAtualizando um Quarto...");

            Quarto quartoAtualizado = new Quarto(numero, tipo, preco, status);
            quartoDAO.atualizarQuarto(quartoAtualizado);

        } catch(SQLException e){
            System.out.println("Erro ao atualizar o quarto: " + e.getMessage());
        }
    }

    private static void removerQuarto(Scanner sc, QuartoDAO quartoDAO){
        System.out.println("Informe o número do quarto a ser removido: ");
        int numero = sc.nextInt();

        try{
            quartoDAO.removerQuarto(numero);
        } catch(SQLException e){
            System.out.println("Erro ao remover o quarto: " + e.getMessage());
        }
    }

    public static void listarQuartos(QuartoDAO quartoDAO){
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