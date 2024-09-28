package dao;

import models.Quarto;
import config.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {

    public void criarQuarto(Quarto quarto) throws SQLException {
        String sql = "INSERT INTO quarto (numero, tipo, preco, status) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, quarto.getNumero());
                stmt.setString(2, quarto.getTipo());
                stmt.setDouble(3, quarto.getPreco());
                stmt.setBoolean(4, quarto.getStatus());
                stmt.executeUpdate();
             }
    }

    public List<Quarto> listarQuartos() throws SQLException {
        String sql = "SELECT * FROM quarto";
        List<Quarto> quartos = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Quarto quarto = new Quarto(rs.getInt("numero"), 
                                               rs.getString("tipo"), 
                                               rs.getDouble("preco"), 
                                               rs.getBoolean("status"));
                                               
                    quartos.add(quarto);
                }

                return quartos;
            }
    
    }

    public void atualizarQuarto(Quarto quarto) throws SQLException{
        String sql = "UPDATE quarto set numero = ?, tipo = ?, preco = ?, status = ? WHERE numero = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, quarto.getNumero());
                stmt.setString(2, quarto.getTipo());
                stmt.setDouble(3, quarto.getPreco());
                stmt.setBoolean(4, quarto.getStatus());

                int rowsUpdate = stmt.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println("Quarto atualizado com sucesso!");
                } else{
                    System.out.println("Nenhum quarto encontrado com o número informado!");
                }

        }
    }

    public void removerQuarto(int numero) throws SQLException{
        String sql = "DELETE FROM quarto WHERE numero = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, numero);
                
                int rowsDeleted = stmt.executeUpdate();
                if(rowsDeleted > 0){
                    System.out.println("Quarto removido com sucesso!");
                } else{
                    System.out.println("Nenhum quarto encontrado com o número informado!");
                }
        } 
    }

    public Quarto visualizarQuarto(int numero) throws SQLException{
        String sql = "SELECT * FROM quarto WHERE numero = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, numero);

                try(ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        return new Quarto(rs.getInt("numero"), 
                                          rs.getString("tipo"), 
                                          rs.getDouble("preco"), 
                                          rs.getBoolean("status"));
                    } else{
                        System.out.println("Nenhum quarto encontrado com o número informado!");
                        return null;
                    }
                }
            }
    }
}