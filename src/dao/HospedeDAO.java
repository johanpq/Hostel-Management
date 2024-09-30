package dao;

import config.ConnectionFactory;
import models.Hospede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {
    public void criarHospede(Hospede hospede) throws SQLException{
        String sql = "INSERT INTO hospede (nome, documento, telefone, email, senha, alimentoRestrito) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, hospede.getNome());
                stmt.setString(2, hospede.getDocumento());
                stmt.setString(3, hospede.getTelefone());
                stmt.setString(4, hospede.getEmail());
                stmt.setString(5, hospede.getSenha());
                stmt.setString(6, hospede.getAlimentoRestrito());
                stmt.executeUpdate();
            }
    }

    public void atualizarHospede(Hospede hospede) throws SQLException {
        String sql = "UPDATE hospede SET nome = ?, documento = ?, telefone = ?, email = ?, senha = ?, alimentoRestrito = ? WHERE documento = ?";
    
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
                stmt.setString(1, hospede.getNome());
                stmt.setString(2, hospede.getDocumento());
                stmt.setString(3, hospede.getTelefone());
                stmt.setString(4, hospede.getEmail());
                stmt.setString(5, hospede.getSenha());
                stmt.setString(6, hospede.getAlimentoRestrito());
                stmt.executeUpdate();
        }
    }
    
    public Hospede vizualizarHospede(String documento) throws SQLException {
        String sql = "SELECT * FROM hospede WHERE documento = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, documento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Hospede(rs.getString("nome"), 
                                       rs.getString("documento"), 
                                       rs.getString("telefone"), 
                                       rs.getString("email"),
                                       rs.getString("senha"),
                                       rs.getString("alimentoRestrito"));
                }
            }
        }
        return null; // Retorna null se o hóspede não for encontrado
    }   
    
    public void removerHospede(String documento) throws SQLException {
        String sql = "DELETE FROM hospede WHERE documento = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, documento);
            stmt.executeUpdate();
        }
    }    

    public List<Hospede> listarHospedes() throws SQLException {
        String sql = "SELECT * FROM hospede";
        List<Hospede> hospedes = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Hospede hospede = new Hospede(rs.getString("nome"), 
                                                    rs.getString("documento"), 
                                                    rs.getString("telefone"), 
                                                    rs.getString("email"),
                                                    rs.getString("senha"),
                                                    rs.getString("alimentoRestrito"));
                    hospedes.add(hospede);
                }
                return hospedes;
        }
    }
}
