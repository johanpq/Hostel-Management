package dao;

import models.Usuario;
import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome_usuario, documento_usuario, telefone_usuario, email_usuario, senha_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
             
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getDocumento());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getSenha());
            pstmt.executeUpdate();
            System.out.println("Usuário " + usuario.getNome() + " adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }
}


