package tech.ada.nuclea.poo.exercicios;

public class Atleta {
    private String cpf;
    protected String dataNascimento;
    protected String melhorTempo;
    public String nome;
    public int quantidadeParticipacoes;

    public static void main(String[] args) {
        Atleta atleta = new Atleta();

        atleta.nome = "João Pedro";
        atleta.quantidadeParticipacoes = 3;
    }
}
