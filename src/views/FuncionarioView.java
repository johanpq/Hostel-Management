package views;

import models.Funcionario;
import dao.FuncionarioDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FuncionarioView {
    public static void gerenciarFuncionarios(){
        Scanner sc = new Scanner(System.in);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        boolean run = true;

        while (run) {
            System.out.println("\nGerenciamento de Funcionários\n");
            System.out.println("1. Criar Funcionário");
            System.out.println("2. Visualizar Funcionário");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Listar todos os Funcionários");
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
                    criarFuncionario(sc, funcionarioDAO);
                    break;  
                case 2:
                    visualizarFuncionario(sc, funcionarioDAO);
                    break;
                case 3:
                    atualizarFuncionario(sc, funcionarioDAO);
                    break;
                case 4:
                    removerFuncionario(sc, funcionarioDAO);
                    break;
                case 5:
                    listarFuncionarios(funcionarioDAO);
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
        sc.close();
    }

    private static void criarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Cargo: ");
        String cargo = sc.nextLine();
        System.out.println("\nCriando um novo funcionário...");

        Funcionario funcionario = new Funcionario(nome, documento, telefone, email, senha, cargo);
        
        try{
            funcionarioDAO.criarFuncionario(funcionario);
            System.out.println("Funcionário criado com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar o funcionário: " + e.getMessage());
        }
    }

    private static void visualizarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        sc.nextLine();
        System.out.println("Informe o documento do funcionário a ser visualizado: ");
        String documento = sc.nextLine();

        try{
            Funcionario funcionario = funcionarioDAO.visualizarFuncionario(documento);
            if (funcionario == null) {
                System.out.println("\nNenhum funcionário encontrado com o documento informado!");
            } else{
                System.out.println(funcionario);
            }
        } catch(SQLException e){
            System.out.println("Erro ao visualizar o funcionário: " + e.getMessage());
        }
    }

    private static void atualizarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        sc.nextLine();
        System.out.println("Informe o documento do funcionário a ser atualizado: ");
        String documento = sc.nextLine();

        try{
            Funcionario funcionarioExistente = funcionarioDAO.visualizarFuncionario(documento);
            if (funcionarioExistente == null) {
                System.out.println("Funcionário não encontrado!");
                return;
            }

            System.out.println("Novo Nome do Funcionário: ");
            String nome = sc.nextLine();
            System.out.println("Novo Telefone do Funcionário: ");
            String telefone = sc.nextLine();
            System.out.println("Novo Email do Funcionário: ");
            String email = sc.nextLine();
            System.out.println("Nova Senha do Funcionário: ");
            String senha = sc.nextLine();
            System.out.println("Novo Cargo do Funcionário: ");
            String cargo = sc.nextLine();
            System.out.println("\nAtualizando um Funcionário...");

            Funcionario funcionarioAtualizado = new Funcionario(nome, documento, telefone, email, senha, cargo);
            funcionarioDAO.atualizarFuncionario(funcionarioAtualizado);

        } catch(SQLException e){
            System.out.println("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    private static void removerFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        sc.nextLine();
        System.out.println("Informe o documento do funcionário a ser removido: ");
        String documento = sc.nextLine();

        try{
            funcionarioDAO.removerFuncionario(documento);
        } catch(SQLException e){
            System.out.println("Erro ao remover o funcionário: " + e.getMessage());
        }
    }

    private static void listarFuncionarios(FuncionarioDAO funcionarioDAO){
        System.out.println("\nListando todos os Funcionários...");
        try{
            List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
            if (funcionarios.isEmpty()) {
                System.out.println("Nenhum Funcionário encontrado!");
            } else{
                for (Funcionario funcionario : funcionarios) {
                    System.out.println(funcionario);
                }
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar os funcionários: " + e.getMessage());
        }
    }
}
