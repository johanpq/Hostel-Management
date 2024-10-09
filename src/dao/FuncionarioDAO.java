package dao;

import models.Funcionario;
import config.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void criarFuncionario(Funcionario fucionario) throws SQLException{
        String sql = "INSERT INTO funcionario (nome, documento, telefone, email, senha, cargo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, fucionario.getNome());
                stmt.setString(2, fucionario.getDocumento());
                stmt.setString(3, fucionario.getTelefone());
                stmt.setString(4, fucionario.getEmail());
                stmt.setString(5, fucionario.getSenha());
                stmt.setString(6, fucionario.getCargo());
                stmt.executeUpdate();
             }
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> fucionarios = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Funcionario funcionario = new Funcionario(rs.getString("nome"), 
                                                              rs.getString("documento"), 
                                                              rs.getString("telefone"), 
                                                              rs.getString("email"),
                                                              rs.getString("senha"),
                                                              rs.getString("cargo"));
                    fucionarios.add(funcionario);
                }

                return fucionarios;
            }
    }

    public void atualizarFuncionario(Funcionario funcionario) throws SQLException{
        String sql = "UPDATE funcionario set nome = ?, telefone = ?, email = ?, senha = ?, cargo = ? WHERE documento = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getTelefone());
                stmt.setString(3, funcionario.getEmail());
                stmt.setString(4, funcionario.getSenha());
                stmt.setString(5, funcionario.getCargo());
                stmt.setString(6, funcionario.getDocumento());

                int rowsUpdate = stmt.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println("Funcionário atualizado com sucesso!");
                } else{
                    System.out.println("Nenhum funcionário encontrado com o documento informado!");
                }

            }
    }

    public void removerFuncionario(String documento) throws SQLException{
        String sql = "DELETE FROM funcionario WHERE documento = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, documento);
                
                int rowsDeleted = stmt.executeUpdate();
                if(rowsDeleted > 0){
                    System.out.println("Funcionário removido com sucesso!");
                } else{
                    System.out.println("Nenhum funcionário encontrado com o documento informado");
                }
        } 
    }

    public Funcionario visualizarFuncionario(String documento) throws SQLException{
        String sql = "SELECT * FROM funcionario WHERE documento = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, documento);

                try(ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        return new Funcionario(rs.getString("nome"),
                                               rs.getString("documento"),
                                               rs.getString("telefone"), 
                                               rs.getString("email"),
                                               rs.getString("senha"),
                                               rs.getString("cargo"));
                    } else{
                        return null;
                    }
                }
            }
    }
    
}
