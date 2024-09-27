package dao;

import models.Reserva;
import config.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void criarReserva(Reserva reserva) throws SQLException{
        String sql = "INSERT reserva (numeroReserva, dataEntrada, dataSaida) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

           stmt.setInt(1, reserva.getNumeroReserva());
           stmt.setString(2, reserva.getDataEntrada());
           stmt.setString(3, reserva.getDataSaida()); 
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
                    Reserva reserva = new Reserva(rs.getInt("numeroReserva"),
                                                  rs.getString("dataEntrada"),
                                                  rs.getString("dataSaida"));
 
                    reservas.add(reserva);
        }
        return reservas;
      
    }
}

    public void atualizarReserva(Reserva reserva) throws SQLException{
        String sql = "UPDATE reserva set numeroReserva = ?, dataEntrada = ?, dataSaida = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, reserva.getNumeroReserva());
                stmt.setString(2, reserva.getDataEntrada());
                stmt.setString(3, reserva.getDataSaida());
                stmt.executeUpdate();
         

    }
}

    public void cancelarReserva(int numeroReserva) throws SQLException{

        String sql = "DELETE FROM reserva WHERE numeroReserva = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, numeroReserva);

                int reservDeleted = stmt.executeUpdate();
                if(reservDeleted > 0){
                    System.out.println(" Sua Reserva foi cancelada com sucesso!");
                } else{
                    System.out.println(" Nao foi possivel encontrar o numero de sua reserva. Por favor, tente novamente!");
                }

    }
}

public Reserva visualizarReserva(int numeroReserva) throws SQLException{
    String sql = "SELECT * FROM quarto WHERE numeroReserva = ?";

    try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, numeroReserva);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    return new Reserva(rs.getInt("numeroReserva"), 
                                      rs.getString("dataEntrada"), 
                                      rs.getString("dataSaida"));
                                      
                } else{
                    System.out.println("Nenhum numero de reserva corresponde ao que voce informou");
                    return null;
                }
            }
        }
}

public String verificarDisponibilidade(int numeroQuarto, String dataEntrada, String dataSaida) throws SQLException {
    String sql = "SELECT COUNT(*) FROM reserva WHERE numeroQuarto = ? " +
                 "AND ((dataEntrada <= ? AND dataSaida >= ?) OR " +
                 "(dataEntrada <= ? AND dataSaida >= ?))";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, numeroQuarto); // numero do quarto a ser verificado
        stmt.setString(2, dataEntrada); // Data da entrada desejada
        stmt.setString(3, dataEntrada); // Data de entrada usada para verificar se há comflitos
        stmt.setString(4, dataSaida);  // Data de Saída
        stmt.setString(5, dataSaida);  // Data de Saida usada para verificar se há conflitos 

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int cont = rs.getInt(1);
                if (cont > 0) {
                    System.out.println("O quarto não está disponível nas datas informadas.");
                } else {
                    System.out.println("O quarto está disponível para as datas informadas.");
                }
            }
        }
    }
    return null;
}
}
