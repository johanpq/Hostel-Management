package models;

public class Reserva {
    private int numeroReserva;
    private String dataEntrada;
    private String dataSaida;


    public Reserva(int numeroReserva, String dataEntrada, String dataSaida) {
        this.numeroReserva = numeroReserva;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
 
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }
}
