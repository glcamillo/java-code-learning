import java.util.Scanner;

public class ProjetoFinalGersonSAC {
    public static void main(String[] args) {

        System.out.printf("\n == Tabela SAC == \n");

        // Leitura de dados do usuário
        Scanner leitor = new Scanner(System.in);

        System.out.println("Informe o valor do empréstimo: ");
        double valorEmprestimo = leitor.nextDouble();

        System.out.println("Informe a taxa de juros (%/mês) do empréstimo: ");
        double taxaJurosEmprestimo = leitor.nextDouble();
        double taxaJurosPercentual = taxaJurosEmprestimo * 0.01;

        System.out.println("Informe o tempo em meses para pagamento empréstimo: ");
        int numMeses = leitor.nextInt();

        // Término da entrada de dados do usuário
        leitor.close();

        // Cálculo da tabela SAC para dados de entrada do usuário
        double saldoDevedor = (double) valorEmprestimo;
        double amortizacao = (double) saldoDevedor / numMeses;
        double jurosMensal = 0.0D;
        double prestacaoMensal = 0.0D;
        double totalPago = 0.0D;

        System.out.printf("Valor fixo da amortização R$ %.2f, "
                + "Saldo devedor total R$ %.2f "
                + "com um juros de %.2f%% ao mês.\n",
                amortizacao, saldoDevedor, taxaJurosEmprestimo);

        for (int i = 0; i < numMeses; i++) {
            jurosMensal = saldoDevedor * taxaJurosPercentual;
            prestacaoMensal = jurosMensal + amortizacao;
            totalPago += prestacaoMensal;
            saldoDevedor = saldoDevedor - amortizacao;

            System.out.printf("Parcela %d | Juros: R$ %.2f | "
                    + "Prestação: R$ %.2f | "
                    + "Saldo devedor: R$ %.2f\n",
                    i+1, jurosMensal, prestacaoMensal, saldoDevedor);
        }
        System.out.printf("Total: Prestação R$ %.2f\n", totalPago);
    }
}
