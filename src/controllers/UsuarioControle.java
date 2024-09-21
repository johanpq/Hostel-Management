package controllers;

import models.Usuario;
import dao.UsuarioDAO;

public class UsuarioControle {

    public static void cadastrarUsuario(String nome, String documento, String telefone, String email, String senha) {
        Usuario usuario = Usuario.criarUsuario(nome, documento, telefone, email, senha);
        UsuarioDAO usuarioDao = new UsuarioDAO(); // Instancia usuarioDAO para poder chamar o método
        usuarioDao.adicionarUsuario(usuario); // Inseri no banco de dados
    }
}
