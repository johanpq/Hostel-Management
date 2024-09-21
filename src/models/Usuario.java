package models;

public class Usuario {
    private String nome;
    private String documento;
    private String telefone;
    private String email;
    private String senha;

    public Usuario(String nome, String documento, String telefone, String email, String senha) {
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public static Usuario criarUsuario(String nome, String documento, String telefone, String email, String senha) {
        return new Usuario(nome, documento, telefone, email, senha);
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
