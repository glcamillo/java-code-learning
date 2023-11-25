import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static java.lang.Double.MAX_VALUE;

/* Entrada:
    param: double input[0]: preço final de fábrica
    param: double input[0]: valor do custo com o distribuidor
    param: double input[0]: valor do preço dos impostos
  Saída:
    percentCustoDist: percentual do custo do distribuidor
    percentCustImpostos: percentual do custo com os impostos
*/
public class Solution
{
    public static void main(String[] args) {
        double[] a = {100000.00,12000.00, 20000.00};
        double[] r = new double[2];
        r = custosCarro(a);
        System.out.println(Arrays.toString(r));

        double[] b = {115500.00,25000.00, 32500.00};
        r = custosCarro(b);
        System.out.println(Arrays.toString(r));

        // Exercício dois
        int[] triangulo1 = {2, 2, 5};
        System.out.println(eUmTriangulo(triangulo1));
        int[] triangulo2 = {3, 3, 5};
        System.out.println(eUmTriangulo(triangulo2));

        // Exercício três
        double[] compra1 = {20.0, 40.0};
        // Saída: Valor total: 60.0 | Valor de descontos: 10.0 | Valor a pagar: 50.0
        String result1 = geraRecibo(compra1);
        System.out.println(result1);

        double[] compra2 = {20.0, 40.0, 30.0};
        String result2 = geraRecibo(compra2);
        // Saída: Valor total: 90.0 | Valor de descontos: 10.0 | Valor a pagar: 80.0
        System.out.println(result2);

        double[] compra3 = {20.0, 80.0, 60.0, 40.0};
        String result3 = geraRecibo(compra3);
        // Saída: Valor total: 200.0 | Valor de descontos: 10.0 | Valor a pagar: 190.0
        System.out.println(result3);

        // Exercicio 4
        int[] idade1 = {5, 4, 14};   // Saída: 1959
        System.out.println(idadeEmDias(idade1));
        int[] idade2 = {10, 8, 16};   // Saída: 3906
        System.out.println(idadeEmDias(idade2));

        // Exercício 7
        double[] vendaMes1 = {3, 20000.00, 2000.00, 250.00};  // Saída: 3750.00
        System.out.println(salarioComComissao(vendaMes1));
        double[] vendaMes2 = {4, 25000.00, 3500.00, 100.00};  //  Saída: 5150.00
        System.out.println(salarioComComissao(vendaMes2));
        double[] vendaMes3 = {4, 25000.00, 3500.00, 0.25789041};  //  Saída:
        System.out.println(salarioComComissao(vendaMes3));

    }

    /* Venda e comissão sobre venda: 5% sobre total; valor fixo por carro vendido
        Entrada: array com: número de carros vendidos/mês, valor total das vendas/mês, salário fixo, valor fixo/carro
        Saída: salário fixo mais comissões
        Arredonde o valor de retorno para duas casas decimais.

        Entrada: [3, 20000.00, 2000.00, 250.00]   Saída: 3750.00
        Entrada: [4, 25000.00, 3500.00, 100.00]    Saída: 5150.00
    */
    public static double salarioComComissao( double[] input)
    {
        double salarioMes = 0;

        salarioMes = input[2] + input[0] * input[3] + input[1] * 0.05;

        byte NUM_PLACES = 2;  // duas casas decimais
        RoundingMode roundingMode = RoundingMode.HALF_UP;  // modo de arrendondamento: 4.455 -> 4.46
        BigDecimal salarioMesBD = new BigDecimal(Double.toString(salarioMes));
        salarioMesBD = salarioMesBD.setScale(NUM_PLACES, roundingMode);

        // Impressão do resultado usando arrendamento de saída impressão
        System.out.printf("salario mês: %f, %.2f \n", salarioMes, salarioMesBD.doubleValue());

        return salarioMesBD.doubleValue();


    }

    /* Calcula dias de vida.
        Entrada: array [anos,meses,dias]
        Saída: idade (inteiro) em dias
        Obs.: ano:365 dias    mês:30 dias.

        Entrada: [5, 4, 14]   Saída: 1959
        Entrada: [10, 8, 16]  Saída: 3906
     */
    public static int idadeEmDias(int[] input)
    {
        int contaDias = input[2];
        contaDias = contaDias + input[1] * 30;
        contaDias = contaDias + input[0] * 365;

        return contaDias;
    }


    /* Compra de duas peças: a de menor valor -> desconto 50%
    Entrada: array double de preço das peças
    Saídas (saída formatada como string):
      - valor total sem descontos
      - valor total de descontos
      - valor total a pagar
     */
    public static String geraRecibo(double[] input) {
        double MAIOR_VALOR_VENDA = Double.MAX_VALUE;

        double menorValor = MAIOR_VALOR_VENDA;
        int indiceMenorValor = 0;
        for (int i =0; i < input.length; i++)
            if (input[i] < menorValor) {
                menorValor = input[i];
                indiceMenorValor = i;
            }
        double valorTotal = 0, valorDescontos = 0, valorAPagar = 0;
        for (int j =0; j < input.length; j++) {
            valorTotal = valorTotal + input[j];
            // Índice j do vetor contém o menor valor
            if (indiceMenorValor == j)
                valorDescontos = input[j] * 0.5;
        }
        valorAPagar = valorTotal - valorDescontos;

        System.out.printf("Valor total: %.1f | Valor de descontos: %.1f | Valor a pagar: %.1f\n",
                valorTotal, valorDescontos, valorAPagar);

        return String.format("Valor total: %.1f | Valor de descontos: %.1f | Valor a pagar: %.1f",
                valorTotal, valorDescontos, valorAPagar);
    }


    public static boolean eUmTriangulo(int[] input)
    {
        //  Para formar um triângulo, o valor de cada lado
        //   deve ser menor que a soma dos outros 2 lados.

        // Teste para o PRIMEIRO lado
        boolean eTrianguloLadoUm = false;
        if (input[0] < (input[1] + input[2]))
            eTrianguloLadoUm = true;

        // Teste para o SEGUNDO lado
        boolean eTrianguloLadoDois = false;
        if (input[1] < (input[0] + input[2]))
            eTrianguloLadoDois = true;

        // Teste para o TERCEIRO lado
        boolean eTrianguloLadoTres = false;
        if (input[2] < (input[0] + input[1]))
            eTrianguloLadoTres = true;

        return (eTrianguloLadoUm && eTrianguloLadoDois && eTrianguloLadoTres);
    }

    public static double[] custosCarro(double[] input)
    {
        byte NUM_PLACES = 2;  // duas casas decimais
        RoundingMode roundingMode = RoundingMode.HALF_UP;  // modo de arrendondamento: 4.455 -> 4.46

        double percentCustoDist = (double) (input[1] * 100) / input[0];
        double percentCustImpostos = (double) (input[2] * 100) / input[0];

        // Impressão do resultado usando arrendamento de saída impressão
        System.out.printf("[%.2f, %.2f]\n", percentCustoDist, percentCustImpostos);

        double[] precCustos = new double[2];

        // Solução para arrendomento com DUAS CASAS: classe BigDecimal
        BigDecimal percCustoDistInBigDec = new BigDecimal(Double.toString(percentCustoDist));
        percCustoDistInBigDec = percCustoDistInBigDec.setScale(NUM_PLACES, roundingMode);
        precCustos[0] = percCustoDistInBigDec.doubleValue();

        BigDecimal percCustoImpostosInBigDec = new BigDecimal(Double.toString(percentCustImpostos));
        percCustoImpostosInBigDec = percCustoImpostosInBigDec.setScale(NUM_PLACES, roundingMode);
        precCustos[1] = percCustoImpostosInBigDec.doubleValue();

        return precCustos;


        /* Problema com DIFERENÇA arredonamento: enunciado vs printf(%.2f) e BigDecimal
        System.out.printf("%f %f \n", percentCustoDist, percentCustImpostos);
        ==> 21,645022 28,138528
        System.out.printf("[%.2f, %.2f]\n", percentCustoDist, percentCustImpostos);
        Mesma saída do arredondamento BigDecimal
        ==> [21,65, 28,14]
        Valor apresentado no enunciado:
        ==> Saída: [21.64, 28.14]
        */
    }
}
