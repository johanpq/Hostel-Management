package models;

/* Classe para representar um quarto de uma pousada.
 * Um quarto tem numero, tipo, preço e status
 * O atributo status é para definir a disponibilidade do quarto.
*/

public class Quarto{
    private int numero;
    private String tipo;
    private double preco;
    private boolean status;

    public Quarto(int numero, String tipo, double preco, boolean status){
        this.numero = numero;
        this.tipo = tipo;
        this.preco =preco;
        this.status = status;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public double getPreco(){
        return preco;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public String toString() {
        String res = "";
        res += "       Funcionário       ";
        res += "=========================";
        res += "Número: " + getNumero() + "\n";
        res += "Tipo: " + getTipo() + "\n";
        res += "Preço: " + getPreco() + "\n";
        res += "Status: " + getStatus() + "\n";
        res += "=========================";
        return res;
    }

}