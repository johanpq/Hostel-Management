package views;

import dao.HospedeDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import models.Hospede;

public class HospedeView {
    public static void gerenciarHospede() {
        Scanner sc = new Scanner(System.in);
        HospedeDAO hospedeDAO = new HospedeDAO();
        boolean run = true;

        while (run) {
            System.out.println("\nGerenciamento de Hospedes\n");
            System.out.println("1. Criar Hospede\n");
            System.out.println("2. Visualizar Hospede\n");
            System.out.println("3. Atualizar Hospede\n");
            System.out.println("4. Remover Hospede\n");
            System.out.println("5. Listar todos os Hospedes\n");
            System.out.println("6. Voltar ao menu principal\n");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após o nextInt()

            switch (opcao) {
                case 1:
                    criarHospede(sc, hospedeDAO);
                    break;
                case 2:
                    visualizarHospede(sc, hospedeDAO);
                    break;
                case 3:
                    atualizarHospede(sc, hospedeDAO);
                    break;
                case 4:
                    removerHospede(sc, hospedeDAO);
                    break;
                case 5:
                    listarHospedes(hospedeDAO);
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
        sc.close(); 
    }

    private static void criarHospede(Scanner sc, HospedeDAO hospedeDAO) {
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Documento: ");
        String documento = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();
        System.out.println("Alimento Restrito: ");
        String alimentoRestrito = sc.nextLine();
        System.out.println("\nCriando um novo Hospede...");

        // Cria o objeto Hospede com os novos parâmetros
        Hospede hospede = new Hospede(nome, documento, telefone, email, senha, alimentoRestrito);

        try {
            hospedeDAO.criarHospede(hospede);  
            System.out.println("Hospede criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar o hospede: " + e.getMessage());
        }
    }

    private static void visualizarHospede(Scanner sc, HospedeDAO hospedeDAO) {
        System.out.println("Informe o documento do hospede a ser visualizado: ");
        String documento = sc.nextLine();

        try {
            Hospede hospede = hospedeDAO.vizualizarHospede(documento);  
            if (hospede != null) {
                System.out.println("Hóspede encontrado: \n\n" + hospede);
            } else {
                System.out.println("Hóspede não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao visualizar o hóspede: " + e.getMessage());
        }
    }

    private static void atualizarHospede(Scanner sc, HospedeDAO hospedeDAO) {
        System.out.println("Informe o documento do hospede a ser atualizado: ");
        String documento = sc.nextLine();

        try {
            Hospede hospedeExistente = hospedeDAO.vizualizarHospede(documento);
            if (hospedeExistente == null) {
                System.out.println("Hospede não encontrado!");
                return;
            }

            System.out.println("Novo Nome do Hospede: ");
            String nome = sc.nextLine();
            System.out.println("Novo Telefone do Hospede: ");
            String telefone = sc.nextLine();
            System.out.println("Novo Email do Hospede: ");
            String email = sc.nextLine();
            System.out.println("Nova Senha do Hospede: ");
            String senha = sc.nextLine();
            System.out.println("Novo Alimento Restrito: ");
            String alimentoRestrito = sc.nextLine();
            System.out.println("\nAtualizando um Hospede...");

            // Cria o objeto Hospede atualizado
            Hospede hospedeAtualizado = new Hospede(nome, documento, telefone, email, senha, alimentoRestrito);
            hospedeDAO.atualizarHospede(hospedeAtualizado);
            System.out.println("Hospede atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o hospede: " + e.getMessage());
        }
    }

    private static void listarHospedes(HospedeDAO hospedeDAO) {
        System.out.println("\nListando todos os Hospedes...");
        try {
            List<Hospede> hospedes = hospedeDAO.listarHospedes();
            if (hospedes.isEmpty()) {
                System.out.println("Nenhum Hospede encontrado!");
            } else {
                for (Hospede hospede : hospedes) {
                    System.out.println(hospede);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os hospedes: " + e.getMessage());
        }
    }

    private static void removerHospede(Scanner sc, HospedeDAO hospedeDAO) {
        System.out.println("Informe o documento do hospede a ser removido: ");
        String documento = sc.nextLine();

        try {
            hospedeDAO.removerHospede(documento);
            System.out.println("Hóspede removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover o hóspede: " + e.getMessage());
        }
    }
}
