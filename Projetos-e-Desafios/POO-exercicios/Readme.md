
## Programação Orientado a Objetos I
### Prof.: Rodolfo Ferreira

### Data: Dezembro 2023

### 1. Orientação a Objetos 18

Em seu ambiente de trabalho alguns colegas deixaram de seguir algumas boas práticas para o controle de visibilidade de atributos importantes e que não deveriam ser alterados em outras partes do código. Após algumas entregas, problemas começaram a surgir na funcionalidade de atualização do saldo e alguns clientes começaram a ficar com saldo negativo sem possuir cheque especial. Quem fez as últimas alterações no código esqueceu de colocar uma validação na alteração do atributo Saldo.

Faça as alterações na classe ContaBancaria de forma que nenhum desenvolvedor consiga realizar visualizar ou alterar o Saldo sem utilizar um método de getSaldo ou setSaldo. O método getSaldo será utilizado para consultar o valor do saldo, enquanto o setSaldo deverá realizar a atualização do valor do tipo double passado via parâmetro, garantindo que a conta não ficará negativa. Caso a atualização de saldo seja para um valor negativo, devolver a mensagem "Saldo insuficiente". Demais casos retornar "Saldo atualizado".
Resultados

### 2. Orientação a Objetos 16

Ao longo de 2020 e 2021, diversas competições esportivas foram postergadas devido à pandemia. Com o passar do tempo, muitos organizadores tiveram que readequar seus cadastros de atletas para conseguir dimensionar corretamente a estrutura necessária para realização do evento no ano de 2022 e com isso alguns problemas surgiram. Para evitar erros, decidiram construir um sistema que auxilie nessa tarefa de controle de informações e você foi escolhido para realizar o levantamento de classes para este sistema.

Uma das classes necessárias será a "Atleta". Esta classe terá todas as informações sobre o atleta que pretende competir determinada prova. Alguns dados podem e devem ser abertos ao acesso geral dentro do código, pois são informações abertas e que podem ser utilizadas para criação de campanhas de marketing, porém outras deverão ser restritas somente à classes derivadas do Atleta e também teremos informações confidenciais que deverão ser informadas apenas durante o cadastro e não poderão ser alteradas ou acessadas em nenhum outra parte do código.

Abaixo você encontrará o nome, tipo e descritivo de acesso do atributo:

```
nome (String): aberto para utilização geral em qualquer parte do código.
dataNascimento (String): restrito apenas para classes que sejam derivadas do Atleta, pois existem pessoas que não se sentem confortáveis em compartilhar essa informação.
cpf (String): informação restrita e que não pode ser acessada ou alterada em nenhum outro lugar, exceto a própria classe Atleta.
melhorTempo (String): informação restrita apenas para classes derivadas do Atleta.
quantidadeParticipacoes (int): informação aberta para acesso e atualização de qualquer lugar do código.
```

Observações:

Não é necessário criar um construtor para a classe Atleta. Caso seja criada, é obrigatória a existência de um construtor sem nenhum parâmetro.

Considerando a descrição da classe e seus atributos necessários, construa uma entidade que atenda os requisitos de atributos e seus respectivos modificadores de acesso.
Resultados

### 3. Orientação a Objetos 17

No ambiente de trabalho é bastante comum existir um processo de code review (revisão de código), onde um colega de trabalho revisa o código de outro em busca de problemas, inconformidades ou até mesmo para sugerir otimizações.

Você foi designado para realizar a revisão do código de um colega, porém durante a execução dos testes unitários você percebeu que havia algum comportamento estranho: os valores passados por parâmetro no construtor não estavam sendo atribuídos corretamente aos atributos do objeto. Faça a análise do código a seguir e aplique a correção adequada para o problema descrito.
Resultados
Solução
1
2
3
4
5
6
7
8

### 4. Orientação a Objetos 19

O código no template tem um problema de implementação que permite que qualquer desenvolvedor consiga realizar alterações nos atributos sem seguir a regra de negócio implementada no setter da classe. Faça a correção do código para que todos os acessos aos atributos da classe sejam feitos, exclusivamente, através dos getters e setters.

```java
public class ContaBancaria {
    public String agencia;
    public int conta;
    public double saldo;
    public double getSaldo() {
        return this.saldo;
    }
    public String setSaldo(double valor) {
        if(valor >= 0) {
            this.Saldo = valor;
            return "Saldo atualizado";
        }
        else {
            return "Saldo insuficiente";
        }
    }
}
```

### 5. Orientação a Objetos 20

Crie uma classe `Usuario` com um atributo privado `senhas` e um método `alterarSenha`.

Caso a nova senha seja igual as três últimas fornecidas, o método alterarSenha deverá retornar "Senha não pode ser igual as três últimas utilizadas";

Caso seja diferente, deve retornar "Senha alterada com sucesso".

