import java.util.Random;

// Gerar 100 números aleatórios e contar a quantidade de números negativos

public class Desafio3String {
    public static void main(String[] args) {
        Random geradorNumAleatorios = new Random();

        byte contadorNegativos = 0;
        for (int i = 0; i < 100; i++) {
            if (geradorNumAleatorios.nextInt() < 0)
                contadorNegativos++;
        }
        System.out.println("Quantidade de números negativos: " + contadorNegativos);
    }
}
/*
System.out.print("Digite um valor decimal valido");
while (!scanner.hasNextDouble()) {
    System.out.println("entrada invalida .. ");
    scanner.next();
}
double valor = scanner.nextDouble();
 */
/*
public class DesafiosStrings {

    public static void main(String[] args) {
        // String fornecida
        String palavra = " Leão-Marinho ";

        // 01. Obter o tamanho do texto da variável palavra01
        int tamanho = palavra.length();
        System.out.println("01. Tamanho da string: " + tamanho);

    }
}
*/

/*
mini-desafios adicionais, enquanto o tempo durar.

Sempre após a mudança imprimir o resultado.
Implementar um algoritmo onde o método main deve ter a solução completa.

String palavra01 = " Leão-Marinho "

01. Obter o tamanho do texto da variavel palavra01
02. Trocar o caracter com acento por um equivalente sem acento
03. Separar em duas string por hifen e imprimi-las separadamente
04. Obter a posicao do caracter i
05. Obter a string do item 02 e retornar se sao iguais
06. Pegar as duas strings separadas no item 03 e juntar com metodo concat
07. Colocar tudo em minusculo
08. Remover os espaços em branco nas laterais e
09. Após item 08 verificar se a variável termina com `nho` e indicar se sim ou não
10. Verificar se palavra01 é nula ou vazia (em branco)

 */