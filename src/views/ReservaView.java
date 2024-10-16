package views;

import dao.ReservaDAO;
import dao.QuartoDAO;
import dao.HospedeDAO;
import dao.FuncionarioDAO;
import models.Reserva;
import models.Quarto;
import models.Hospede;
import models.Funcionario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservaView {
    public static void gerenciarReservas() throws SQLException{
        Scanner sc = new Scanner(System.in);
        ReservaDAO reservaDAO = new ReservaDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        QuartoDAO quartoDAO = new QuartoDAO();
        HospedeDAO hospedeDAO = new HospedeDAO();
        boolean run = true;

        while (run) {
            System.out.println("\nGerenciamento de Reservas\n");
            System.out.println("1. Criar Reserva");
            System.out.println("2. Visualizar Reserva");
            System.out.println("3. Atualizar Reserva");
            System.out.println("4. Remover Reserva");
            System.out.println("5. Listar todas as Reservas");
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
                    criarReserva(sc, reservaDAO, funcionarioDAO, quartoDAO, hospedeDAO);
                    break;  
                case 2:
                    visualizarReserva(sc, reservaDAO);
                    break;
                case 3:
                    atualizarReserva(sc, reservaDAO);
                    break;
                case 4:
                    removerReserva(sc, reservaDAO);
                    break;
                case 5:
                    listarReservas(reservaDAO);
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    continue;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
    }

    private static void criarReserva(Scanner sc, ReservaDAO reservaDAO, FuncionarioDAO funcionarioDAO, QuartoDAO quartoDAO, HospedeDAO hospedeDAO) throws SQLException{
        System.out.println("Número da Reserva: ");
        int numeroReserva = sc.nextInt();
        System.out.println("Data de entrada(AAAA-MM-DD): ");
        LocalDate dataEntrada = LocalDate.parse(sc.nextLine());
        System.out.println("Data de Saída(AAAA-MM-DD): ");
        LocalDate dataSaida = LocalDate.parse(sc.nextLine());

        System.out.println("Informe o documento do funcionário: ");
        String documentoF = sc.nextLine();
        Funcionario funcionario = funcionarioDAO.visualizarFuncionario(documentoF);
        if (funcionario == null) {
            System.out.println("Nenhum funcionário encontrado com o documento informado!");
            return;
        }

        System.out.println("Informe o número do quarto: ");
        int numeroQuarto = sc.nextInt();
        Quarto quarto = quartoDAO.visualizarQuarto(numeroQuarto);
        if (quarto == null) {
            System.out.println("Nenhum quarto encontrado com o número informado!");
            return;
        }

        System.out.println("Informe o documento do hóspede: ");
        String documentoH = sc.nextLine();
        Hospede hospede = hospedeDAO.vizualizarHospede(documentoH);
        if (hospede == null) {
            System.out.println("Nenhum hóspede encontrado com o documento informado!");
            return;
        }

        Reserva reserva = new Reserva(numeroReserva, dataEntrada, dataSaida, funcionario, quarto, hospede);
        try{
            reservaDAO.criarReserva(reserva);
            System.out.println("Reserva criada com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar a reserva: " + e.getMessage());
        }
    }

    private static void visualizarReserva(Scanner sc,ReservaDAO reservaDAO){
        System.out.println("Informe o número da reserva a ser visualizada: ");
        int numeroReserva = sc.nextInt();

        try{
            Reserva reserva = reservaDAO.visualizarReserva(numeroReserva);
            if (reserva == null) {
                System.out.println("\nNenhuma reserva encontrada com o número informado!");
            } else {
                System.out.println(reserva);
            }
        } catch(SQLException e){
            System.out.println("Erro ao visualizar a reserva: " + e.getMessage());
        }
    }

    private static void atualizarReserva(Scanner sc, ReservaDAO reservaDAO){
        System.out.print("Número da Reserva: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();

        try {
            Reserva reservaExistente = reservaDAO.visualizarReserva(numeroReserva);
            if (reservaExistente == null) {
                System.out.println("\nNenhuma reserva encontrada com o número informado!");
                return;
            }

            System.out.print("Nova Data de Entrada (AAAA-MM-DD): ");
            LocalDate novaDataEntrada = LocalDate.parse(sc.nextLine());
            System.out.print("Nova Data de Saída (AAAA-MM-DD): ");
            LocalDate novaDataSaida = LocalDate.parse(sc.nextLine());

            reservaExistente.setDataEntrada(novaDataEntrada);
            reservaExistente.setDataSaida(novaDataSaida);
            reservaDAO.atualizarReserva(reservaExistente);

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar a reserva: " + e.getMessage());
        }
    }

    private static void removerReserva(Scanner sc, ReservaDAO reservaDAO){
        System.out.println("Informe o número da reserva a ser removido: ");
        int numeroReserva = sc.nextInt();

        try{
            reservaDAO.removerReserva(numeroReserva);
        } catch(SQLException e){
            System.out.println("Erro ao remover a reserva: " + e.getMessage());
        }
    }

    private static void listarReservas(ReservaDAO reservaDAO){
        System.out.println("\nListando todas as Reservas...");
        try{
            List<Reserva> reservas = reservaDAO.listarReservas();
            if (reservas.isEmpty()) {
                System.out.println("Nenhuma reserva encontrada!");
            } else{
                for (Reserva reserva : reservas) {
                    System.out.println(reserva);
                }
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar as reservas: " + e.getMessage());
        }
    }
}