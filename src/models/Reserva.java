package models;

import java.time.LocalDate;

public class Reserva {
    private int numeroReserva;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Funcionario funcionario;
    private Quarto quarto;
    private Hospede hospede;

    public Reserva(int numeroReserva, LocalDate dataEntrada, LocalDate dataSaida, Funcionario funcionario, Quarto quarto, Hospede hospede) {
        this.numeroReserva = numeroReserva;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.funcionario = funcionario;
        this.quarto = quarto;
        this.hospede = hospede;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
 
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public String toString() {
        String res = "";
        res += "         Reserva         \n";
        res += "=========================\n";
        res += "Número da Reserva: " + getNumeroReserva() + "\n";
        res += "Data de Entrada: " + getDataEntrada() + "\n";
        res += "Data de Saída: " + getDataSaida() + "\n";
        res += "Funcionario: " + funcionario.getNome() + "\n";
        res += "Quarto: " + quarto.getNumero() + "\n";
        res += "Hóspede: " + hospede.getNome() + "\n";
        res += "=========================";
        return res;
    }
}
