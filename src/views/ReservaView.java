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
                    atualizarReserva(sc, reservaDAO, funcionarioDAO, quartoDAO, hospedeDAO);
                    break;
                case 4:
                    removerReserva(sc, reservaDAO, quartoDAO);
                    break;
                case 5:
                    listarReservas(reservaDAO);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção Inválida. Tente novamente!");
                    break;
            }
        }
    }

    public static void criarReserva(Scanner sc, ReservaDAO reservaDAO, FuncionarioDAO funcionarioDAO, QuartoDAO quartoDAO, HospedeDAO hospedeDAO){
        System.out.println("Número da Reserva: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();
        try{
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
            sc.nextLine();
            Quarto quarto = quartoDAO.visualizarQuarto(numeroQuarto);
            if (quarto == null) {
                System.out.println("Nenhum quarto encontrado com o número informado!");
                return;
            }
            if (!quarto.getStatus()){
                System.out.println("O quarto já está reservado!");
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
        
            reservaDAO.criarReserva(reserva);
            quarto.setStatus(false);
            quartoDAO.atualizarStatusQuarto(quarto);
            System.out.println("Reserva criada com sucesso!");
        } catch(SQLException e){
            System.out.println("Erro ao criar a reserva: " + e.getMessage());
        }
    }

    private static void visualizarReserva(Scanner sc,ReservaDAO reservaDAO){
        System.out.println("Informe o número da reserva a ser visualizada: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();

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

    private static void atualizarReserva(Scanner sc, ReservaDAO reservaDAO, FuncionarioDAO funcionarioDAO, QuartoDAO quartoDAO, HospedeDAO hospedeDAO){
        System.out.print("Número da Reserva: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();

        try {
            Reserva reservaExistente = reservaDAO.visualizarReserva(numeroReserva);
            if (reservaExistente == null) {
                System.out.println("\nNenhuma reserva encontrada com o número informado!");
                return;
            }

            Quarto quartoAtual = reservaExistente.getQuarto();

            System.out.print("Nova Data de Entrada (AAAA-MM-DD): ");
            LocalDate novaDataEntrada = LocalDate.parse(sc.nextLine());
            System.out.print("Nova Data de Saída (AAAA-MM-DD): ");
            LocalDate novaDataSaida = LocalDate.parse(sc.nextLine());

            System.out.println("Informe o documento do novo funcionário: ");
            String documentoF = sc.nextLine();
            Funcionario funcionario = funcionarioDAO.visualizarFuncionario(documentoF);
            if (funcionario == null) {
                System.out.println("Nenhum funcionário encontrado com o documento informado!");
                return;
            }

            System.out.println("Informe o número do novo quarto: ");
            int numeroQuarto = sc.nextInt();
            sc.nextLine();
            Quarto quarto = quartoDAO.visualizarQuarto(numeroQuarto);
            if (quarto == null) {
                System.out.println("Nenhum quarto encontrado com o número informado!");
                return;
            }
            if (!quarto.getStatus()){
                System.out.println("O quarto já está reservado!");
                return;
            }
            if (quartoAtual.getNumero() != quarto.getNumero()) {
                quartoAtual.setStatus(true);
                quartoDAO.atualizarStatusQuarto(quartoAtual);

                quarto.setStatus(false);
                quartoDAO.atualizarStatusQuarto(quarto);
            }

            System.out.println("Informe o documento do novo hóspede: ");
            String documentoH = sc.nextLine();
            Hospede hospede = hospedeDAO.vizualizarHospede(documentoH);
            if (hospede == null) {
                System.out.println("Nenhum hóspede encontrado com o documento informado!");
                return;
            }

            Reserva reserva = new Reserva(numeroReserva, novaDataEntrada, novaDataSaida, funcionario, quarto, hospede);
            reservaDAO.atualizarReserva(reserva);

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar a reserva: " + e.getMessage());
        }
    }

    private static void removerReserva(Scanner sc, ReservaDAO reservaDAO, QuartoDAO quartoDAO){
        System.out.println("Informe o número da reserva a ser removida: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();

        try{
            Reserva reserva = reservaDAO.visualizarReserva(numeroReserva);
            if (reserva == null) {
                System.out.println("Nenhuma reserva encontrada com o numero informado!");
                return;
            }
            Quarto quarto = reserva.getQuarto();

            reservaDAO.removerReserva(numeroReserva);
            quarto.setStatus(true);
            quartoDAO.atualizarStatusQuarto(quarto);
        } catch(SQLException e){
            System.out.println("Erro ao remover a reserva: " + e.getMessage());
        }
    }

    public static void removerReserva(Scanner sc, String documentoAutenticado, ReservaDAO reservaDAO, QuartoDAO quartoDAO){
        System.out.println("Informe o documento: ");
        String documento = sc.nextLine();

        if(!documento.equals(documentoAutenticado)) {
            System.err.println("O seu documento não é o que está em nosso banco de dados!");
            return;
        }

        System.out.println("Informe o número da reserva a ser removida: ");
        int numeroReserva = sc.nextInt();
        sc.nextLine();

        try{
            Reserva reserva = reservaDAO.visualizarReserva(numeroReserva);
            if (reserva == null) {
                System.out.println("Nenhuma reserva encontrada com o numero informado!");
                return;
            }
            Quarto quarto = reserva.getQuarto();

            reservaDAO.removerReserva(numeroReserva);
            quarto.setStatus(true);
            quartoDAO.atualizarStatusQuarto(quarto);
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