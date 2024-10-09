package models;

/* Classe para representar um funcionario de uma pousada.
 * Um funcionario tem nome, documento, telefone, email, senha e cargo
*/

public class Funcionario extends Usuario {
    private String cargo;

    public Funcionario(String nome, String documento, String telefone, String email, String senha, String cargo){
        super(nome, documento, telefone, email, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String toString() {
        String res = "";
        res += "       Funcionário       \n";
        res += "=========================\n";
        res += "Nome: " + getNome() + "\n";
        res += "Documento: " + getDocumento() + "\n";
        res += "Telefone: " + getTelefone() + "\n";
        res += "Email: " + getEmail() + "\n";
        res += "Senha: " + getSenha() + "\n";
        res += "Cargo: " + getCargo() + "\n";
        res += "=========================";
        return res;
    }

}