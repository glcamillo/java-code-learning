import java.util.Scanner;

public class ProjetoFinalGersonConvTemp {
    public static void main(String[] args) {

        System.out.printf("\n == Conversor de Temperaturas == \n");

        Scanner leitor = new Scanner(System.in);

        System.out.println("Informe a temperatura: ");
        double tempOrigem = leitor.nextDouble();

        // Ler unidade da temperatura de ORIGEM
        String unidadeOrigem = null;
        boolean entradaValida = true;
        do {
            System.out.println("Qual a unidade de origem dessa temperatura? ");
            System.out.println("[C, K, F]");
            unidadeOrigem = leitor.next();
            if (unidadeOrigem.equals("C") || unidadeOrigem.equals("K")
                    || unidadeOrigem.equals("F")) {
                entradaValida = true;
            } else {
                System.out.printf("\n Unidade de temperatura ORIGEM (%s) inválida.\n",
                        unidadeOrigem);
                entradaValida = false;
            }
        } while (!entradaValida);

        // Ler unidade de temperatura DESTINO
        String unidadeDestino = null;
        // Usando variável sentinela definida anteriormente: entradaValida
        do {
            System.out.println("Qual a unidade de destino dessa temperatura? ");
            System.out.println("[C, K, F]");
            unidadeDestino = leitor.next();
            if (unidadeDestino.equals("C") || unidadeDestino.equals("K")
                    || unidadeDestino.equals("F")) {
                entradaValida = true;
            } else {
                System.out.printf("\n Unidade de temperatura DESTINO (%s) inválida\n",
                        unidadeDestino);
                entradaValida = false;
            }
        } while (!entradaValida);

        // Realizar conversão de temperaturas conforme unidades
        //   informadas pelo usuário
        double tempDestino = 0.0D;
        if (unidadeOrigem.equals("F")) {
            if (unidadeDestino.equals("C")) {
                tempDestino = (double)((tempOrigem - 32)*5)/9;
            } else if (unidadeDestino.equals("K")) {
                // Conversão de F para C
                double tempCelsius = (double)((tempOrigem - 32)*5)/9;
                // Conversão de C para K
                tempDestino = (double) 273.15 + tempCelsius;
            } else if (unidadeDestino.equals("F")) {
                tempDestino = (double)tempOrigem;
            }
        } else if (unidadeOrigem.equals("C")) {
            if (unidadeDestino.equals("F")) {
                tempDestino = (double) ((tempOrigem*9)/5)+32;
            } else if (unidadeDestino.equals("K")) {
                tempDestino = (double) 273.15 + tempOrigem;
            } else if (unidadeDestino.equals("C")) {
                tempDestino = (double)tempOrigem;
            }
        } else if (unidadeOrigem.equals("K")) {
            if (unidadeDestino.equals("F")) {
                // Conversão de K para C
                double tempCelsius = (double) tempOrigem - 273.15;
                // Conversão de C para F
                tempDestino = (double) ((tempOrigem*9)/5)+32;
            } else if (unidadeDestino.equals("C")) {
                tempDestino = (double) tempOrigem - 273.15;
            } else if (unidadeDestino.equals("K")) {
                tempDestino = (double) tempOrigem;
            }
        }

        System.out.printf("%.2f %s = %.2f %s\n", tempOrigem, unidadeOrigem,
                tempDestino, unidadeDestino);

        leitor.close();
    }
}