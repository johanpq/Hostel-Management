package models;

/* Classe para representar um funcionario de uma pousada.
 * Um funcionario tem nome, documento, telefone, email, senha, cargo e admin 
 * O atributo admin é para definir um administrador.
*/

public class Funcionario extends Usuario {
    private String cargo;
    private boolean admin;

    public Funcionario(String nome, String documento, String telefone, String email, String senha, String cargo, boolean admin){
        super(nome, documento, telefone, email, senha);
        this.cargo = cargo;
        this.admin = admin;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String toString() {
        String res = "";
        res += "       Funcionário       ";
        res += "=========================";
        res += "Nome: " + getNome() + "\n";
        res += "Documento: " + getDocumento() + "\n";
        res += "Telefone: " + getTelefone() + "\n";
        res += "Email: " + getEmail() + "\n";
        res += "Senha: " + getSenha() + "\n";
        res += "Cargo: " + getCargo() + "\n";
        res += "Admin: " + getAdmin() + "\n";
        res += "=========================";
        return res;
    }

}