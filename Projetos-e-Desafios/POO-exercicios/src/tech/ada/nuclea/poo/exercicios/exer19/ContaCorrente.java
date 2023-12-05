package tech.ada.nuclea.poo.exercicios.exer19;

public class ContaCorrente {
    private String agencia;
    private int conta;
    private double saldo;
    public double getSaldo() {
        return this.saldo;
    }
    public String setSaldo(double valor) {
        if(valor >= 0) {
            this.saldo = valor;
            return "Saldo atualizado";
        }
        else {
            return "Saldo insuficiente";
        }
    }
    void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    void setConta(int conta) {
        this.conta = conta;
    }
    String getAgencia() {
        return agencia;
    }
    int getConta() {
        return conta;
    }
}
