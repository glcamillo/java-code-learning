package tech.ada.nuclea.poo.exercicios;

public class ContaBancaria {
    public String agencia;
    public int conta;
    public double saldo;

    String setSaldo(double saldo) {
        if (this.saldo + saldo < 0)
            return "Saldo insuficiente";
        else {
            this.saldo = saldo;
            return "Saldo atualizado";
        }
    }
    double getSaldo() {
        return this.saldo;
    }
}

/* Faça as alterações na classe ContaBancaria de forma que nenhum desenvolvedor
        consiga realizar visualizar ou alterar o Saldo sem utilizar um método de
        getSaldo ou setSaldo.
  O método getSaldo será utilizado para consultar o valor do saldo, enquanto o
        setSaldo deverá realizar a atualização do valor do tipo double passado
        via parâmetro, garantindo que a conta não ficará negativa. Caso a
        atualização de saldo seja para um valor negativo, devolver a mensagem
        "Saldo insuficiente". Demais casos retornar "Saldo atualizado".
*/