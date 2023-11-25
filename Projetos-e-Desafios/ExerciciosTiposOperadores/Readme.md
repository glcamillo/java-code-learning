** Exercício 1**

Operadores

Maria está olhando o mercado de automóveis para comprar um carro novo. Ela identificou que o preço final do veiculo tem um percentual relacionado ao distribuidor e também um percentual de impostos. Ela deseja identificar qual veículo possui os menores percentuais de imposto e do distribuidor, para fazer a escolha de seu carro novo.

Para ajudar Maria com a sua busca, crie uma função que receba um array com o preço de final de fábrica, o valor do custo com o distribuidor e o valor do preço dos impostos. Ao final, a função deve retornar um array com o percentual do custo do distribuidor e o percentual do custo com os impostos, seguindo essa ordem. Realize o arredondamento para duas casas decimais em relação aos dados de retorno.

Valores para teste:

Entrada: [100000.00, 12000.00, 20000.00]
Saída: [12.00, 20.00]

Entrada: [115500.00,25000.00, 32500.00]
Saída: [21.64, 28.14]

A função deverá ser criada seguindo a estrutura abaixo:

public class Solution
{
public static double[] custosCarro(double[] input)
{
// Seu código aqui
}

*** Exercício 2 ***
Condicionais, Operadores

Crie uma função que receberá um array com três valores que correspondem a possíveis lados de um triângulo. Na função, validar se os dados fornecidos formam um triângulo. Caso afirmativo, retornar true; do contrario, retornar false.

OBS: para formar um triângulo, o valor de cada lado deve ser menor que a soma dos outros 2 lados.

Valores para teste:

Entrada: [2,2,5]
Saída: false

Entrada: [3,3,5]
Saída: true

*** Exercício 3 ***

Arrays, Ordenação, Operações aritméticas

Uma loja de roupas decidiu aquecer suas vendas lançando uma promoção com descontos especiais para clientes que comprassem mais de uma peça. As condições da promoção dizem que, na compra de duas peças, será dado 50% de desconto na peça de menor de valor.

Escreva uma função que receba um array contendo o preço das peças de um cliente e calcule o valor total sem descontos, o valor total de descontos, e o valor total a pagar. A saída da função deve ser uma string.

Ex:
Entrada: [20.0, 40.0]
Saída: Valor total: 60.0 | Valor de descontos: 10.0 | Valor a pagar: 50.0

Ex:
Entrada: [20.0, 40.0, 30.0]
Saída: Valor total: 90.0 | Valor de descontos: 10.0 | Valor a pagar: 80.0

Ex:
Entrada: [20.0, 80.0, 60.0, 40.0]
Saída: Valor total: 200.0 | Valor de descontos: 10.0 | Valor a pagar: 190.0

A função deverá ser criada seguindo a estrutura abaixo:

public class Solution
{
public static String geraRecibo(double[] input)
{
/// Seu código aqui
}
}

*** Exercício 4 ***

Operadores

Um certo dia, Joãozinho chegou em casa e perguntou para sua mãe quantos dias ele tinha de vida. A mãe disse que esse era um dado que ela tinha que parar para fazer a conta. Para ajudar a mãe de Joãozinho, crie uma função que recebe a idade expressa em anos, meses e dias (os dados de entradas estarão contidos em um array) e retorne a idade da pessoa expressa em dias. Considerar o ano como tendo um total de 365 dias, e o mês como tendo um total de 30 dias.

Ex:
Entrada: [5, 4, 14]
Saída: 1959

Ex:
Entrada: [10, 8, 16]
Saída: 3906

A função deverá ser criada seguindo a estrutura abaixo:

public class Solution
{
public static int idadeEmDias(int[] input)
{
// Seu código aqui
}
}

*** Exercício 7 ***

Operadores

Pedrinho trabalha com vendas de carro. Mensalmente ele recebe um salário fixo e comissões baseadas em suas vendas. As comissões são as seguintes

Comissões:

    5% sobre o valor total vendido no mês
    valor fixo por cada carro vendido

Faça uma função que recebe um array com: número de carros por ele vendidos no mês, o valor total de suas vendas no mês, seu salário fixo, valor fixo que ele recebe por carro vendido. Calcule e retorne qual o salário dele (salário fixo mais comissões). Arredonde o valor de retorno para duas casas decimais.

Ex:
Entrada: [3, 20000.00, 2000.00, 250.00]
Saída: 3750.00

Ex:
Entrada: [4,25000.00, 3500.00, 100.00]
Saída: 5150.00

A função deverá ser criada seguindo a estrutura abaixo:

public class Solution
{
public static double salarioComComissao( double[] input)
{
/// Seu código aqui
}
}




