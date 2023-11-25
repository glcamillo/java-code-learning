
# Projeto Curso: Ser + Tech Programa 50+

Referências do projeto:
1. [https://gist.github.com/rafarocha/c79ce60eb94d3d9ddd2154ca8e291528](https://gist.github.com/rafarocha/c79ce60eb94d3d9ddd2154ca8e291528)
2. [https://pad.riseup.net/p/1JDJ0JDs07YTO5qP8cGt](https://pad.riseup.net/p/1JDJ0JDs07YTO5qP8cGt)

Data de criação: 20231113

# Bingo Game
Construir um jogo de bingo multiplayer para rodar local com opções para sorteio automático aleatório ou manual que ao final indique algumas dados estatísticos

## Game
### 01 - Etapa 1 - Apresentação e Jogadores
1. Mensagem de boas vindas e mostrar opções de comando
1. Dar um design bacana para o visual e dê um nome ao jogo
1. Considerar o modo multiplayer automático com 1 ou mais jogadores
1. Separar por hifen o nickname: player1-player2-player3

### 02 - Etapa 2 - Cartelas
1. Gerar as cartelas desejadas
1. Opções de comando para cartelas geradas RANDOM ou MANUAL
1. Para MANUAL localizar o nickname preencher a cartela
1. O input deverá ser: `"1,2,3,4,5-6,7,8,9,1-2,3,4,5,6"`
1. Para RANDOM será gerado automaticamente aleatórios não repetidos


## Solução de código para as etapas 01 e 02
Configuração e inicialização de estrutura de dados para comportar o jogo.

1. Configuração do jogo: através de variáveis estáticas com valores constantes, conforme:
```jshelllanguage
// Tamanho do jogo
static final int LENGHT_OF_BOARD = 5;
static final int HOW_MANY_NUMBERS_IN_SPINNER = 60;
static final int SPINNER_MIN_NUMBER = 1;
static final int SPINNER_MAX_NUMBER = 60;
```
2. O nome dos jogadores que serão fornecidos como `player1-player2-player3` serão separados via split usando hífen e alocados nas seguintes variáveis: 
```jshelllanguage
static String[] bingoCardPlayers = null;
static int numPlayers = 0;
```

3. Como serão especificados os números da cartela? Manual ou Aleatório. O usuário deverá escolher no início da execução do programa. A seguinte matriz bidimensional definirá as cartelas (segunda dimensão) de cada jogador (primeira dimensão). 
```jshelllanguage
private static int[][] bingoScoreCard;
```
Observação importante: os **jogadores serão referenciados pelos índices** dos seguintes arrays:

- Cartelas: `bingoScoreCard[numPlayers][]`
- Jogo: `bingoScoreCardHITS[numPlayers][]`
- Ganhadores do BINGO: `bingoWinners[numPlayers]`. Em princípio, pode ter mais de um.

Dependendo da escolha do usuário:
- Manual: será lido uma String da entrada Scanner da forma: `1,2,3,4,5-6,7,8,9,1-2,3,4,5,6`. Será usado o método split primeiro com hífen para separar os jogadores e depois cada String terá seus elementos separados por split de vírgula. Assim serão obtidos os valores em String dos números que deverão ser convertidos em inteiros.

- Aleatória: 
```
    //  COM semente: geração de uma mesma sequẽncia: fase TESTES do programa
    private static int seedValueForCards = 65537;
    private static final Random randomForCards = new Random(seedValueForCards);
    private static int seedValueForGame = 27644437;
    private static final Random randomForGame = new Random(seedValueForGame);
    //  SEM semente: cada execução terá diferente sequência: fase PRODUÇÃO
    // private static final Random random = new Random();
    // private static final Random randomForGame = new Random();
```

Observações Importantes sobre **Aleatoriedade**:
- Serão definidos dois geradores de números aleatórios: um para as cartelas (`randomForCards`) e outro para o próprio jogo (`randomForGame`).
- Na inicialização do jogo (no método `main`) será definido como os geradores serão iniciados: com semente (*seed*) ou sem semente. Com semente, será sempre gerada a mesma sequência de números. Sem semente, a cada execução, uma nova sequência será gerada. O primeiro caso, será para testes.



- Manual

```
    // ------  Dados para CONFIGURAÇÃO do JOGO   -----
// Tamanho do jogo
static final int LENGHT_OF_BOARD = 5;
static final int HOW_MANY_NUMBERS_IN_SPINNER = 60;
static final int SPINNER_MIN_NUMBER = 1;
static final int SPINNER_MAX_NUMBER = 60;

// Array para nome de Jogadores
static String[] bingoCardPlayers = null;
static int numPlayers = 0;

// Matriz para cartelas
private static int[][] bingoScoreCard;
```

### 03 - Etapa 3 - Números Sorteados
1. Opções de comando do sorteio podem ser RANDOM ou MANUAL
1. Para MANUAL os números sorteados entrarão via Scanner
1. O input deverá seguir a sintaxe: `"1,2,3,4,5"`
1. Para RANDOM serão números aleatórios não repetidos na cartela
1. A cada round deverá imprimir:
1. Um ranking dos top 3 mais bem pontuados no game
1. Um pedido de pressionar a tecla para continuar via Scanner
1. Se pressionar X aborta o game

## Solução de código para a etapa 03 (EXECUÇÃO DO JOGO)

Serão criadas as seguintes variáveis e estruturas de dados para rastrear os sorteios:

    // Array dos números que PODEM ser sorteados e aqueles já sorteados
```jshelllanguage
    private static int[] bingoSpinnerNumbers;
    private static boolean[][] bingoScoreCardHITS;
    private static boolean[] bingoWinners;
    private static int[] winnersInOrder;           // []=numberHits
    private static int[] winnersInOrderPlayers;    // []=numPlayer
    private static int[] winnersInOrderHits;
    static int numOfRounds = 0;
```
Os números sorteados são rastreados pelo array `bingoSpinnerNumbers` da seguinte forma:

a) Inicialização

```
bingoSpinnerNumbers = [1, 2, 3, 4, 5, 6 ... 55, ..., 58, 59, 60];
```
b) Depois da primeira rodada com os seguintets números sorteados: `2,59,5,60,55`

```
bingoSpinnerNumbers = [1, 0, 3, 4, 0, 6 ... 0, ..., 58, 0, 0];
```
c) A cada rodada o array é percorrido pelo índice para confirmar se já foi sorteado. Próximo sorteio: `2,3,4,58,60`.
Neste caso, os índices (1)(59) contém valores zero, logo, já sorteados. Então, deverão ser sorteados mais dois números.

Cada cartela será rastreada quanto aos números acertados (HITS) pelo seguinte array booleano: `bingoScoreCardHITS`. Ele é um array bidimensional da seguinte forma: `[numPlayers][LENGHT_OF_BOARD]` = `[número de jogadores][tamanho/quantidade de números na cartela]`.

Será inicializado com FALSE e caso um número seja sorteado, será setado em TRUE.


### 04 - Etapa 4 - Fim do Jogo
1. Cada jogador terá um array para indicar os números acertados
1. Esse array indica a posição de cada número na cartela
1. Aqui temos os dois ultimos números acertados, ex: `0,0,0,1,1`
1. O bingo será eleito quando o jogador tiver todos com número 1
1. Ao final do game exibir o ranking geral e estatísticas do game:
    - Quantidade de rounds
    - Cartela premiada com números ordenados e nome do vencedor
    - Quantidade e números sorteados em ordem
    - Ranking geral ordenado pelo número de acertos

## Solução de código para a etapa 03 (EXECUÇÃO DO JOGO)

### 05 - Etapa 5 - Regras Gerais e Sugestões para implementar
1. Não usar classes e derivadas de Collections, use array/matriz
1. No modo manual iremos anunciar as cartelas no pad.riseup
1. Daí cada um marca a sua cartela manualmente para acompanhar
1. Pode usar classes utilitárias do java.util como Random e Arrays
1. Estruture seu código em métodos por responsabilidade
