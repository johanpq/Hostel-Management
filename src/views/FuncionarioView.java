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
        int opcao;
        boolean run = true;

        while (run) {
            System.out.println("\nGerenciamento de Funcionários\n");
            System.out.println("1. Criar Funcionário\n");
            System.out.println("2. Visualizar Funcionário");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Listar todos os Funcionários");
            System.out.println("6. Voltar ao menu principal");
            int opcao = sc.nextInt();

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
                    run = false;
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
    }

    private static void criarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
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
        System.out.println("Cargo: ");
        String cargo = sc.nextLine();
        System.out.println("Administrador(true se é administrador, false se não é administrador): ");
        boolean admin = sc.nextBoolean();
        System.out.println("\nCriando um novo funcionário...");

        funcionarioDAO = new Funcionario(nome, documento, telefone, email, senha, cargo, admin);
        
        try{
            funcionarioDAO.criarFuncionario(funcionarioDAO);
            System.out.println("Funcionário criado com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar o funcionário: " + e.getMessage());
        }
    }

    private static void visualizarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        System.out.println("Informe o doucumento do funcionário a ser visualizado: ");
        String documento = sc.nextLine();

        try{
            funcionarioDAO.visualizarFuncionario(documento);
        } catch(SQLException e){
            System.out.println("Erro ao visualizar o funcionário: " + e.getMessage());
        }
    }

    private static void atualizarFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
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
            System.out.println("Novo Documento do Funcionário: ");
            String documento = sc.nextLine();
            System.out.println("Novo Telefone do Funcionário: ");
            String telefone = sc.nextLine();
            System.out.println("Novo Email do Funcionário: ");
            String email = sc.nextLine();
            System.out.println("Nova Senha do Funcionário: ");
            String senha = sc.nextLine();
            System.out.println("Novo Cargo do Funcionário: ");
            String cargo = sc.nextLine();
            System.out.println("Administrador(true se é administrador, false se não é administrador): ");
            boolean admin = sc.nextBoolean();
            System.out.println("\nAtualizando um Funcionário...");

            Funcionario funcionarioAtualizado = new Funcionario(nome, documento, telefone, email, senha, cargo, admin);
            funcionarioDAO.atualizarFuncionario(funcionarioAtualizado);
            System.out.println("Funcionário atualizado com sucesso!");

        } catch(SQLException e){
            System.out.println("Erro ao atualizar o funcionário: " + e.getMessage());
        }
    }

    private static void removerFuncionario(Scanner sc, FuncionarioDAO funcionarioDAO){
        System.out.println("Informe o documento do funcionário a ser removido: ");
        String documento = sc.nextLine();

        try{
            funcionarioDAO.removerFuncionario(documento);
            System.out.println("Funcionário removido com sucesso!");
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
