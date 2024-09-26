package models;

public class Hospede extends Usuario {
    private String alimentoRestrito;

    public Hospede(String nome, String documento, String telefone, String email, String senha, String alimentoRestrito) {
      super(nome, documento, telefone, email, senha);
      this.alimentoRestrito = alimentoRestrito;
    }
      
    public void setAlimentoRestrito(String alimentoRestrito) {
        this.alimentoRestrito = alimentoRestrito;
    }

    public String getAlimentoRestrito() {
        return alimentoRestrito;
    } 

    public String toString() {
        String res = "";
        super.toString();
        res += "Alimento restrito: " + alimentoRestrito + "\n";
        return res;
    }
}