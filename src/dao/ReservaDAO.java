package dao;

import models.Reserva;
import models.Funcionario;
import models.Hospede;
import models.Quarto;
import config.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void criarReserva(Reserva reserva) throws SQLException{
        String sql = "INSERT reserva (numeroReserva, dataEntrada, dataSaida, documentoFuncionario, numeroQuarto, documentoHospede) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, reserva.getNumeroReserva());
                stmt.setDate(2, reserva.getDataEntrada());
                stmt.setDate(3, reserva.getDataSaida());
                stmt.setString(4, reserva.getFuncionario().getDocumento());
                stmt.setInt(5, reserva.getQuarto().getNumero());
                stmt.setString(6, reserva.getHospede().getDocumento());
                stmt.executeUpdate();
       }
    }

    public List<Reserva> listarReservas() throws SQLException{
        String sql = "SELECT * FROM reserva";
        List<Reserva> reservas = new ArrayList<>();
            
        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String documentoFuncionario = rs.getString("documentoFuncionario");
                    int numeroQuarto = rs.getInt("numeroQuarto");
                    String documentoHospede = rs.getString("documentoHospede");

                    Funcionario funcionario = obterFuncionario(documentoFuncionario);
                    Quarto quarto = obterQuarto(numeroQuarto);
                    Hospede hospede = obterHospede(documentoHospede);

                    Reserva reserva = new Reserva(rs.getInt("numeroReserva"),
                                                  rs.getDate("dataEntrada"),
                                                  rs.getDate("dataSaida"),
                                                  funcionario, quarto, hospede);
 
                    reservas.add(reserva);
                }
                return reservas;
      
        }
    }

    public void atualizarReserva(Reserva reserva) throws SQLException{
        String sql = "UPDATE reserva set dataEntrada = ?, dataSaida = ?, nomeFuncionario = ?, numeroQuarto = ?, nomeHospede = ? WHERE numeroReserva = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setDate(1, Date.valueOf(reserva.getDataEntrada()));
                stmt.setDate(2, Date.valueOf(reserva.getDataSaida()));
                stmt.setString(3, reserva.getFuncionario().getDocumento());
                stmt.setInt(4, reserva.getQuarto().getNumero());
                stmt.setString(5, reserva.getHospede().getDocumento());
                stmt.setInt(6, reserva.getNumeroReserva());
                
                int rowsUpdate = stmt.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println("Reserva atualizada com sucesso!");
                } else{
                    System.out.println("Nenhuma reserva encontrada com o numero informado!");
                }
         
        }
    }

    public void removerReserva(int numeroReserva) throws SQLException{
        String sql = "DELETE FROM reserva WHERE numeroReserva = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, numeroReserva);

                int rowsDeleted = stmt.executeUpdate();
                if(rowsDeleted > 0){
                    System.out.println("Reserva removida com sucesso!");
                } else{
                    System.out.println("Nenhuma reserva encontrada com o número informado!");
                }

        }
    }

    public Reserva visualizarReserva(int numeroReserva) throws SQLException{
        String sql = "SELECT * FROM reserva WHERE numeroReserva = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement();
            ResultSet rs = stmt.executeQuery(sql)){

                stmt.setInt(1, numeroReserva);

                if (rs.next()) {
                    String documentoFuncionario = rs.getString("documentoFuncionario");
                    int numeroQuarto = rs.getInt("numeroQuarto");
                    String documentoHospede = rs.getString("documentoHospede");

                    Funcionario funcionario = obterFuncionario(documentoFuncionario);
                    Quarto quarto = obterQuarto(numeroQuarto);
                    Hospede hospede = obterHospede(documentoHospede);
                    
                    return new Reserva(rs.getInt("numeroReserva"), 
                                       rs.getString("dataEntrada"), 
                                       rs.getString("dataSaida"),
                                       funcionario, quarto, hospede);
                                  
                } else{
                    return null;
                }
        }
    }

    private Funcionario obterFuncionario(String documentoFuncionario) throws SQLException{
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.visualizarFuncionario(documentoFuncionario);
    }

    private Quarto obterQuarto(int numeroQuarto) throws SQLException{
        QuartoDAO quartoDAO = new QuartoDAO();
        return quartoDAO.visualizarQuarto(numeroQuarto);
    }

    private Hospede obterHospede(String documentoHospede) throws SQLException{
        HospedeDAO hospedeDAO = new HospedeDAO();
        return hospedeDAO.vizualizarHospede(documentoHospede);
    }
}