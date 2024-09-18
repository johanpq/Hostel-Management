package models;

/* Classe para representar um funcionario de uma pousada.
 * Um funcionario tem nome, documento, telefone, email e cargo.
*/

public class Funcionario {
    private String nome;
    private String documento;
    private String telefone;
    private String email;
    private String cargo;

    public Funcionario(String nome, String documento, String telefone, String email, String cargo){
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario [nome=" + nome + ", documento=" + documento + ", telefone=" + telefone + ", email=" + email
                + ", cargo=" + cargo + "]";
    }

}