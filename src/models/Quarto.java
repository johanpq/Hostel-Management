package models;

/* Classe para representar um quarto de uma pousada.
 * Um quarto tem numero, tipo, preço e status.
*/

public class Quarto{
    private int numero;
    private String tipo;
    private double preco;
    private String status;

    public Quarto(int numero, String tipo, double preco, String status){
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

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}