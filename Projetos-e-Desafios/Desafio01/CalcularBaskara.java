import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

/*  Implemente um algoritmo que retorne o resultado da formula de báskara,
        para a entrada de três variáveis (a, b, c), o qual deverá retornar a saída dos
        valores de x1 e x2.
        Responda esta atividade colocando abaixo o código completo.
        Considere verificar entrada de dados válidos, inválidos e decimais.
        */
/* Fórmula báskara para cálculo de raiz de segundo grau
    ax^2 + bx + c = 0
    Fórmula raiz (x) = [-b +/- sqrt(b^2 - 4ac)] / 2a

    Lido da entrada: a,b,c -> double
    Saída: valores de x
 */
public class CalcularBaskara {

    public static void main(String[] args) {

        System.out.println("====     Fórmula de báskara: x = [-b +/- sqrt(b^2 - 4ac)] / 2a    ===");
        System.out.println("= Deverão ser fornecidos valores em ponto flutuante para a, b e c. = ");

        double a, b, c;
        a = obtemDadoEntrada('a');
        if (a == 0.0) {
            System.out.println("O coeficiente a sendo igual a ZERO implica que equação não é de segundo grau. Saindo...");
            return;
        }
        b = obtemDadoEntrada('b');
        c = obtemDadoEntrada('c');

        double x1, x2;
        x1 = calcularRaiz (a, b, c, "positiva");
        System.out.printf("\n Raiz positiva x1 = %.3f", x1);
        x2 = calcularRaiz (a, b, c, "negativa");
        System.out.printf("\n Raiz negativa x2 = %.3f", x2);
    }

    private static double calcularRaiz(double a, double b, double c, String sinal) {
        double delta = pow(b, 2) - 4*a*c;
        if (delta < 0) {
            System.out.println("Discriminante negativo: raiz não real. Saindo ...");
            System.exit(-1);
        }
        if (sinal.equals("positiva")) {
            double x1 = -b + Math.sqrt(delta);
            return x1;
        } else {
            double x2 = -b - Math.sqrt(delta);
            return x2;
        }
    }

    static double obtemDadoEntrada(char coeficiente) {
        double valorDouble = 0;
        Scanner leitor = new Scanner(System.in);
        boolean entradaInvalida = true;
        while (entradaInvalida) {
            System.out.printf("\nEntre com um número para '%c': ", coeficiente);
            try {
                valorDouble = leitor.nextDouble();
                entradaInvalida = false;
            } catch (InputMismatchException e) {
                System.out.println("Erro no valor de entrada.");
                leitor.nextLine();
            }
        }
        return valorDouble;
    }
}
