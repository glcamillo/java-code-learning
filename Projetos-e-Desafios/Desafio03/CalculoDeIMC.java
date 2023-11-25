import java.util.Scanner;

/*   Desafio 03 - Cálculo IMC, Classificação e Peso Ideal
    ### Etapa 01 - obter peso e altura e calcular IMC
    ### Etapa 02 - Classificação de Peso
    ### Etapa 03 - Peso Ideal por IMC limite máximo de 24,90
    ### Etapa 04 - Faixa de Peso Ideal
    # Regras
     Indicar a classificação utilizando na lógica enum e switch
     Utilizar uma constante ou enum para IMC ideal no valor indicado na etapa 3
 */

public class CalculoDeIMC {
    static final double ALTURA_MIN = 0.3; // altura mínima 30 cm
    static final double ALTURA_MAX = 3.0; // alttura máxima 3 m
    static final double PESO_MIN = 20.0;   // mínimo 20kg
    static final double PESO_MAX = 250;   // espero que ninguém pese mais
    private enum classesDeImc  { MAGREZA, NORMAL, SOBREPESO, OBESIDADE_I, OBESIDADE_II, OBESIDADE_III }

    public static void main(String[] args) {

        System.out.println("====>     Cálculo de IMC     <===");
        System.out.println("=> Deverão ser fornecidos valores em ponto flutuante para altura (metros) e peso (kg)");
        System.out.println("=> Exemplo: altura=1,72   peso=88,88");

        double altura, peso;

        try {
            altura = obtemDadoEntrada("altura");
        } catch (Exception e){
            System.out.println("Erro na entrada de dados. Saindo...");
            return;
        }
        boolean dadoValido = validaDadoEntrada(altura, "altura");
        if (!dadoValido) {
            System.out.println("Dado de altura fora da faixa 0,3 - 3 m. Saindo...");
            return;
        }

        try {
            peso = obtemDadoEntrada("peso");
        } catch (Exception e) {
            System.out.println("Erro na entrada de dados. Saindo...");
            return;
        }
        dadoValido = validaDadoEntrada(peso, "peso");
        if (!dadoValido)  {
            System.out.println("Dado de peso fora da faixa 20 - 250 kg. Saindo...");
            return;
        }

        System.out.println("-------      Resultados      -------");
        // ### Etapa 1 - cálculo de IMC
        double imc = peso / (altura * altura);  // IMC (kg/m^2)
        // Saída arredondada para dois dígitos
        System.out.printf("#Etapa1: O valor do seu IMC é %2.2f kg/m^2.\n", imc);

        // ### Etapa 02 - Classificação de Peso
        String classificacaoIMC = null;
        classesDeImc classeDeImc = null;
        if (imc < 18.5)
            classeDeImc = classesDeImc.MAGREZA;
        else if (imc >= 18 && imc <= 24.9)
            classeDeImc = classesDeImc.NORMAL;
        else if (imc >= 25 && imc <= 29.9)
            classeDeImc = classesDeImc.SOBREPESO;
        else if (imc >= 30 && imc <= 34.9)
            classeDeImc = classesDeImc.OBESIDADE_I;
        else if (imc >= 35 && imc <= 39.9)
            classeDeImc = classesDeImc.OBESIDADE_II;
        else if (imc >= 40)
            classeDeImc = classesDeImc.OBESIDADE_III;

        imprimeClassificacaoIMC(classeDeImc);

        // ### Etapa 03 - Peso Ideal por IMC: limite de imc <= 24.9
        double pesoIdeal = (altura*altura)*24.9;
        System.out.printf("#Etapa3: O peso máximo para sua altura de %.2f m é igual a %.2f kg.\n", altura, pesoIdeal);

        // ### Etapa 04 - Faixa de Peso Ideal
        double pesoIdealMin = (altura*altura)*18.5;
        System.out.printf("#Etapa4: A faixa de peso para sua altura de %.2f m é entre: %.1f a %.1f kg.\n", altura,
                pesoIdealMin, pesoIdeal);
    }

    private static void imprimeClassificacaoIMC(classesDeImc classeDeImc) {
        String classificacaoIMC = null;
        switch (classeDeImc) {
            case MAGREZA:
                classificacaoIMC = "Magreza";
                break;
            case NORMAL:
                classificacaoIMC = "Normal";
                break;
            case SOBREPESO:
                classificacaoIMC = "Sobrepeso";
                break;
            case OBESIDADE_I:
                classificacaoIMC = "Obesidade grau I";
                break;
            case OBESIDADE_II:
                classificacaoIMC = "Obesidade grau II";
                break;
            case OBESIDADE_III:
                classificacaoIMC = "Obesidade grau III";
                break;
        }

        System.out.println("#Etapa2: A classificação do IMC é: " + classificacaoIMC);
    }


    private static boolean validaDadoEntrada(double valor, String tipoEntrada) {
        if (tipoEntrada.equals("altura")) {
            if (valor < ALTURA_MIN || valor > ALTURA_MAX)
                return false;
        }
        if (tipoEntrada.equals("peso")) {
            if (valor < PESO_MIN || valor > PESO_MAX)
                return false;
        }
        return true;
    }


    private static double obtemDadoEntrada(String valorLidoDaEntrada) {
        double valorDouble = 0;
        Scanner leitor = new Scanner(System.in);
        boolean entradaInvalida = true;
        while (entradaInvalida) {
            System.out.printf("\nEntre com o dado para '%s': ", valorLidoDaEntrada);
            try {
                valorDouble = leitor.nextDouble();
                entradaInvalida = false;
            } catch (Exception e) {
                System.out.println("Erro no valor de entrada.");
                leitor.nextLine();
            }
        }
        /* Dúvida aqui: ao fechar o leitor depois da primeira leitura, as próximas geram
                      exceções. Falta pesquisar quando fechar. Ou se usa somente um leitor
                      dentro do contexto do programa e fechar ao fim de main. Deixei
                      comentado aqui.
         leitor.close(); */

        return valorDouble;
    }
}
