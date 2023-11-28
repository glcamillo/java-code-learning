import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*  Desafio 07 - Matrizes - Opcional
Contexto
    O código abaixo resolve o problema de multiplicação de matrizes 2x2.
    Avalie, pesquise e implemente, se necessário, as etapas a seguir
    Esta atividade vale +1 ponto extra
Referência: https://gist.github.com/rafarocha/b3fad761793d8eb4b61d1fb70e146805
Etapa 1
    Implemente antes um algoritmo que cria uma matriz para você dada uma dimensão passada por parâmetro
    Agora faça um algoritmo que permita também multiplicar por 3x3, 5x5, 10x10, 100x100, 1000x1000
Etapa 2
    À medida que a matriz cresce, que mudou na lógica?
    Avalie também pelo JConsole o consumo de recursos por dimensionamentos
        Qual foi o comportamento entre diferentes dimensões?
Etapa 3
    Quantas multiplicações foram realizadas em cada caso? Implemente um contador.
    Há alguma forma de diminuir o número de múltiplicações? Se sim, qual?
        Veja que as operações de adição podem ser realizadas em um único ciclo de clock de cpu
        Operações de multiplicação geralmente exigem vários ciclos de clock


Observações:
a) serão geradas matrizes com VALORES INTEIROS para fins de teste  dos algoritmos.
b) ao final deste arquivo estão alguns RESULTADOS e COMENTÁRIOS/RESPOSTAS

 */

public class MultiplicacaoMatrizes {
    private static int[][] matrizA;
    private static int[][] matrizB;
    private static double[][] produto;

    // Próximas variáveis para rastrear desempenho de execução
    //  Matrizes: 3x3, 5x5, 10x10, 100x100, 1000x1000
    private static final int NUM_TAM_MATRIZES = 5;
    private static int[] dimensoes = new int[]{3, 5, 10, 100, 1000};
    private static int[] numMultiplicacaoes = new int [NUM_TAM_MATRIZES];
    private static long[] tempoExecucaoProdutoMatrizes = new long[NUM_TAM_MATRIZES];


    public static void main(String[] args) {

        // Matrizes de tamanho: 3x3, 5x5, 10x10, 100x100, 1000x1000
        int[] dimensoes = new int[]{3, 5, 10, 100, 1000};

        Scanner entrada = new Scanner(System.in);
        String a = entrada.nextLine();

        // -------   Execução
        // Matrizes de tamanho: 3x3, 5x5, 10x10, 100x100, 1000x1000
        for (int n = 0; n < dimensoes.length; n++ ) {
            matrizA = gerarMatrizBidimensionalDadosAleatorios(dimensoes[n]);
            matrizB = gerarMatrizBidimensionalDadosAleatorios(dimensoes[n]);
            produto = multiplicarMatrizesQuadradas(matrizA, matrizB, n);
            try {
                imprimeMatrizesEResultado(n);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
        }
        imprimeResultados();
    }


    // Multiplicação de matrizes quadradas, logo, linhas iguais colunas
    //    Exemplo:   produto[i][j] = matrizA[i][j] * matrizB[i][j]
    //     Dimensões: i=j
    // Parâmetro: duas matrizes quadradas; indice: qual execução em relação ao
    //     contador de execuções
    static double[][] multiplicarMatrizesQuadradas(int[][] matrizA, int[][] matrizB, int indice) {
        // Parâmetro indice vai permitir obter qual execução
        int dimensao = dimensoes[indice];

        double[][] produto = new double[dimensao][dimensao];

        // Inicializado a cada execução
        numMultiplicacaoes[indice] = 0;

        // Para contabilizar tempo de execução
        long instanteTempoInicial = System.nanoTime();

        for (int i = 0; i < dimensao; i++) { // i-linha
            for (int j = 0; j < dimensao; j++) { // j-coluna
                 for (int k = 0; k < dimensao; k++) { // k-fator
                     produto[i][j] += matrizA[i][k] * matrizB[k][j];
                     numMultiplicacaoes[indice]+=1;
                 }
            }
        }
        long instanteTempoFinal = System.nanoTime();
        tempoExecucaoProdutoMatrizes[indice] = instanteTempoFinal - instanteTempoInicial;
        return produto;
    }
    static int[][] gerarMatrizBidimensionalDadosAleatorios(int dimensao) {
        Random random = new Random();
        int[][] matriz = new int[dimensao][dimensao];

        int numRandom;
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                numRandom = random.nextInt();
                matriz[i][j] = numRandom;
            }
        }
        return matriz;
    }
    private static void imprimeMatrizesEResultado(int dimensao) {
        System.out.printf("\n -----------------------------------------------------  \n");
        System.out.printf(" Produtos de matrizes de dimensão: %d x %d (linhas x colunas)\n",
                dimensao, dimensao);

        if (dimensao > 10)
            throw new RuntimeException("Acho que NÃO quer imprimir na tela" + dimensao +  "LINHAS");

        System.out.println("      Matriz A      ");
        for (int n = 0; n < dimensao; n++)
            System.out.println(Arrays.toString(matrizA[n]));
        System.out.println("      Matriz B      ");
        for (int n = 0; n < dimensao; n++)
            System.out.println(Arrays.toString(matrizB[n]));
        System.out.println("      Matriz Produto     ");
        for (int n = 0; n < dimensao; n++)
            System.out.println(Arrays.toString(produto[n]));
    }

    private static void imprimeResultados() {
        System.out.println();
        System.out.printf("\n ==========================================================  ");
        for (int i = 0; i < dimensoes.length; i++)
            System.out.printf("\n Dimensão: %3d Número Multiplicações: %d  Tempo Execução: %d",
                dimensoes[i], numMultiplicacaoes[i], tempoExecucaoProdutoMatrizes[i]);
    }

}

/* Resultados da simulação usando currentTimeMillis()
 Dimensão: 3 Número Multiplicações: 27  Tempo Execução: 0
 Dimensão: 5 Número Multiplicações: 125  Tempo Execução: 0
 Dimensão: 10 Número Multiplicações: 1000  Tempo Execução: 0
 Dimensão: 100 Número Multiplicações: 1000000  Tempo Execução: 10
 Dimensão: 1000 Número Multiplicações: 1000000000  Tempo Execução: 4557

 Resultados da simulação usando nanoTime()
 Dimensão:   3 Número Multiplicações: 27  Tempo Execução: 3623
 Dimensão:   5 Número Multiplicações: 125  Tempo Execução: 5987
 Dimensão:  10 Número Multiplicações: 1000  Tempo Execução: 39284
 Dimensão: 100 Número Multiplicações: 1000000  Tempo Execução: 9593882
 Dimensão: 1000 Número Multiplicações: 1000000000  Tempo Execução: 5106872759

Como evoluiu o tempo de execução:
dimensão: 5 para 10 (dobrou)  tempo de execução aumentou 6,56 vezes
dimensão: 10 para 100 (10x)   tempo de execução aumentou 244,23 vezes
dimensão: 100 para 1000 (10x) tempo de execução aumentou 532,31 vezes
São aumentos exponenciais.

Usando o JConsole: número de threads iniciou em 15 e não foi alterado com
a execução das multiplicações; uso de CPU não foi impactada (ficou abaixo 0,2%);
uso de heap constante (apesar da criação de objetos de arrays cada vez maiores)

Como melhorar: arrays tem tempo de acesso constante, então depende muito
como a linguagem implementa aritmética de acesso em arrays. Linguagens
como C, permite usar ponteiros, cuja aritmética a nível de código de
máquina tem menos overhead que acesso matriz.

Em Java, o que me vem em primeiro lugar é paralelizar o produto em
threads, já que não há dependência de dados entre os elementos.

 */