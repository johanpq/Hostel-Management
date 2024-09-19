package models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String documento;
    private String telefone;
    private String email;
    private String senha;

    // Permite que as subclasses acessem a lista
    protected static List<Usuario> listaUsuario = new ArrayList<>();

    public Usuario(String nome, String documento, String telefone, String email, String senha) {
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        
        // Cria uma lista de usuários
        listaUsuario.add(this);
    }

    public boolean fazerLogin(String email, String senha) {
        return (this.email.equals(email) && this.senha.equals(senha));
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // Métodos estáticos conseguem ser chamados diretamente pela classe, sem precisar de um objeto criado
    public static void visualizarUsuario(String documento) {
        for (Usuario i : listaUsuario) {
            if(i.getDocumento().equals(documento)) {
                System.out.println(i);
            }
        }
    }

    public static void removerUsuario(String documento) {
        Usuario getUsuario = null;

        // Verifica se existe o usuário pelo documento
        for (Usuario i : listaUsuario) {
            if(i.getDocumento().equals(documento)) {
                getUsuario = i;
                break;
            } else {
                System.out.println("Nao existe este usuario!");
            }
        }

        if(getUsuario != null) {
            listaUsuario.remove(getUsuario);
            System.out.println(getUsuario.nome + "foi removido com sucesso!");
        }
    }

    public String toString() {
        String res = "";
        res += "         Usuario         ";
        res += "=========================";
        res += "Nome: " + getNome() + "\n";
        res += "Documento: " + getDocumento() + "\n";
        res += "Telefone: " + getTelefone() + "\n";
        res += "Email: " + getEmail() + "\n";
        res += "Senha: " + getSenha() + "\n";
        res += "=========================";
        return res;
    }
}
