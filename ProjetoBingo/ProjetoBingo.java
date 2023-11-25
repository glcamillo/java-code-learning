import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

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


    // ------  Estrutura dados para RASTREAR o JOGO   -----

    // Array dos números que PODEM ser sorteados e aqueles
    //     já sorteados (valor zerado)
    private static int[] bingoSpinnerNumbers;

    // Matriz do Jogo: boolean[numPlayers][LENGHT_OF_BOARD]
    //      Será inicializado com zero: caso um número sorteado, setado em UM
    //      [numPlayers][LENGHT_OF_BOARD]
    private static boolean[][] bingoScoreCardHITS;

    // Array dos jogadores que fizeram BINGO

    private static boolean[] bingoWinners;

    // Arrays para rastrear ordem (ranking) decrescente de acertos a cada rodada
    private static int[] winnersInOrder;           // []=numberHits
    private static int[] winnersInOrderPlayers;    // []=numPlayer
    private static int[] winnersInOrderHits;
    static int numOfRounds = 0;

    // Gerador de números pseudoaletórios inicializado:
    //  COM semente: geração de uma mesma sequẽncia: fase TESTES do programa
    private static int seedValueForCards = 65537;
    private static final Random randomForCards = new Random(seedValueForCards);
    private static int seedValueForGame = 27644437;
    private static final Random randomForGame = new Random(seedValueForGame);
    //  SEM semente: cada execução terá diferente sequência: fase PRODUÇÃO
    // private static final Random random = new Random();
    // private static final Random randomForGame = new Random();

    public static void main(String[] args) {

        // Imprime cabeçalho e informações sobre o jogo
        printHeaderForBingo();

        Scanner readFromIn = new Scanner(System.in);

        System.out.printf("\n =====   INICIALIZAÇÃO jogadores e cartelas  ====\n");

        // Inicializa o Girador de Bingo (sacola com bolas de números)
        bingoSpinnerNumbers = new int[HOW_MANY_NUMBERS_IN_SPINNER];
        for (int i = SPINNER_MIN_NUMBER; i < SPINNER_MAX_NUMBER; i++)
            bingoSpinnerNumbers[i-1] = i;    // número=1 então bingoSpinnerNumbers[0]=1

        // Leitura de jogadores
        readPlayersFromInput();

        // Geração das cartelas
        generateBoardCard();

        System.out.println(" --- Jogadores e Cartelas --- ");
        printBoardCard();

        System.out.printf("\n\n =====     INÍCIO do BINGO     ====\n");
        playBingoGame();

        System.out.printf("\n\n =====     Impressão RESULTADOS do BINGO     ====\n");
        printGame();
        printResults();

        readFromIn.close();
    }

    // ---------------------------------------------------------------
    // ====     Saída e controle de ACERTOS(HITS) e GANHADORES  ======
    // ---------------------------------------------------------------

    private static void printResults() {
        // Imprime cabeçalho
        printHeaderForResults();

        System.out.printf("\nNúmero de rodadas: %d\n", numOfRounds);

        boolean foundBingoWinner = findBingoWinner();

        System.out.printf("\nCartela(s) premiada(s):\n");
        if (foundBingoWinner)
            for (int i = 0; i < numPlayers; i++)
                if (bingoWinners[i])
                    System.out.printf("Cartela: %d  Números: %s   Jogador: %s\n",
                            i, Arrays.toString(bingoScoreCard[i]), bingoCardPlayers[i]);

        System.out.printf("\nRelação de números sorteados:\n");
        for (int i =0; i < HOW_MANY_NUMBERS_IN_SPINNER; i++) {
            if (bingoSpinnerNumbers[i] == 0)
                System.out.printf("%2d ", i+1); // bingoSpinnerNumbers[0]
                                                // corresponde bolinha 1
        }

        System.out.printf("\nRanking de acertos:\n");
        for (int i = 0; i < numPlayers; i++)
            System.out.printf("%2d %10s\n", winnersInOrder[i],
                    bingoCardPlayers[winnersInOrderPlayers[i]]);
    }

    private static void orderPlayersByHits() {
        // Atualiza array de jogadores ganhadores (winnersInOrder) com
        //   valores de acertos.
        checkoutNumberOfHits();

        // Reinicia array de índices de Hits
        for (int i = 0; i < numPlayers; i++)
            winnersInOrderPlayers[i] = i;

        // Colocar os números do array em ordem decrescente
        int tempValue = 0;
        int tempIndex = 0;
        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < numPlayers-1; j++) {
                if (winnersInOrder[j+1] > winnersInOrder[j]) {
                    tempValue = winnersInOrder[j];
                    winnersInOrder[j] = winnersInOrder[j+1];
                    winnersInOrder[j+1] = tempValue;
                    tempIndex = winnersInOrderPlayers[j];
                    winnersInOrderPlayers[j] = winnersInOrderPlayers[j+1];
                    winnersInOrderPlayers[j+1] = tempIndex;
                }
            }
        }
    }

    private static void checkoutNumberOfHits() {
        for (int i = 0; i < numPlayers; i++) { // Jogadores -> cartelas
            int countHits = 0;
            for (int j = 0; j < LENGHT_OF_BOARD; j++)
                if (bingoScoreCardHITS[i][j])
                    countHits++;
            winnersInOrder[i] = countHits;
        }
    }

    private static boolean findBingoWinner() {
        boolean foundBindoWinner = false;
        for (int i = 0; i < numPlayers; i++) { // Jogadores -> cartelas
            bingoWinners[i] = false;  // Inicializa array com false
            boolean foundBingoWinner = true;
            for (int j = 0; j < LENGHT_OF_BOARD; j++) {
                if (!bingoScoreCardHITS[i][j]) {
                    foundBingoWinner = false;
                    break;
                }
            }
            if (foundBingoWinner) {
                foundBindoWinner = true;
                bingoWinners[i] = true;
            }
        }
        return foundBindoWinner;
    }

    private static void findAndPrintTop3() {
        System.out.printf("\nTop 3:\n");
        for (int i = 0; i < 3; i++)
            System.out.printf("%2d %10s\n", winnersInOrder[i],
                    bingoCardPlayers[winnersInOrderPlayers[i]]);

    }

    private static void checkoutHits(int[] numbers) {
        for (int n: numbers) {
            for (int i = 0; i < bingoScoreCard.length; i++)
                for (int j = 0; j < bingoScoreCard[i].length; j++)
                    if (n == bingoScoreCard[i][j])
                        bingoScoreCardHITS[i][j] = true;
        }
    }

    // ---------------------------------------------------------------
    //   =========     BINGO -> jogando     ==================
    // ---------------------------------------------------------------

    private static void playBingoGame() {
        bingoScoreCardHITS = new boolean[bingoScoreCard.length][LENGHT_OF_BOARD];

        winnersInOrder = new int[numPlayers];
        winnersInOrderPlayers = new int[numPlayers];
        bingoWinners = new boolean[numPlayers];

        // Inicialização da matriz do jogo com FALSE
        for (int i = 0; i < bingoScoreCard.length; i++)
            for (int j = 0; j < LENGHT_OF_BOARD; j++)
                bingoScoreCardHITS[i][j] = false;

        // Inicialização da matriz da ordem dos ganhadores e
        //   dos ganhadores de bingo
        for (int i = 0; i < bingoScoreCard.length; i++) {
            winnersInOrder[i] = 0;
            winnersInOrderPlayers[i] = i;
            bingoWinners[i] = false;
        }

        // Impressão do jogo inicial
        printGame();

        Scanner readFromIn = new Scanner(System.in);

        System.out.printf("\nb) Sorteio dos números: (M)anual  (A)leatória (X para sair)? ");
        String lineInput = readFromIn.nextLine();
        switch (lineInput.toUpperCase()) {
            case "M":
                playBingoGameManual();
                break;
            case "A":
                playBingoGameRandom();
                break;
            case "X":
                System.out.printf("\n !!!   Saindo ... !!! \n\n");
                System.exit(0);
            default:
                System.out.printf("\n !!! Entrada inválida. Saindo ... !!! \n\n");
        }
        orderPlayersByHits();
    }

    private static void playBingoGameRandom() {
        // Inicialização da matriz do jogo com FALSE
        for (int i = 0; i < numPlayers; i++)
            for (int j = 0; j < LENGHT_OF_BOARD; j++)
                bingoScoreCardHITS[i][j] = false;

        System.out.printf("\n >>>> Sorteio dos números usando ALEATORIEDADE <<<< \n");
        System.out.println("   Tecle <ENTER> para continuar ou X para sair: ");
        Scanner readFromIn = new Scanner(System.in);
        String input = null;
        boolean nextRound = true;

        numOfRounds = 0;
        boolean foundBingoWinner = false;
        int[] numbers = new int[LENGHT_OF_BOARD];

        do {
            input = readFromIn.nextLine();
            if (input.equalsIgnoreCase("x")) {
                nextRound = false;
            } else {
                numbers = genRandomInOrderForGame(LENGHT_OF_BOARD);
                checkoutHits(numbers);
                printNumbers(numbers);
                printGame();
                foundBingoWinner = findBingoWinner();
                numOfRounds++;
            }
            if (foundBingoWinner) {
                printFoundBingoWinner();
                nextRound = false;
            }
            orderPlayersByHits();
            findAndPrintTop3();
        } while (nextRound);
    }


    // Métodos diferentes para geração números aleatórios
    // Aleatoriedade para JOGO: executado em toda a rodada
    private static int[] genRandomInOrderForGame(int howManyNumbers) {
        int[] randomNumbers = new int[howManyNumbers];

        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = 0;

        int numRandom = 0;

        // Obter números aleatórios e checar unicidade dos valores
        boolean success = true;
        for (int i = 0; i < howManyNumbers; i++) {
            // Para sortear números entre 1 e 60 (nextInt(1,61)
            numRandom = randomForGame.nextInt(SPINNER_MIN_NUMBER, SPINNER_MAX_NUMBER+1);
            if (bingoSpinnerNumbers[numRandom-1] != 0) {     // numRandom=10 então
                bingoSpinnerNumbers[numRandom - 1] = 0;      // bingoSpinnerNumbers[9] => 10
                randomNumbers[i] = numRandom;
            }
            else
                i = i - 1;  // repete o sorteio do número
        }

        // Colocar os números do array em ordem crescente
        int tempValue = 0;
        for (int i = 0; i < howManyNumbers; i++) {
            for (int j = 0; j < howManyNumbers-1; j++) {
                if (randomNumbers[j+1] < randomNumbers[j]) {
                    tempValue = randomNumbers[j];
                    randomNumbers[j] = randomNumbers[j+1];
                    randomNumbers[j+1] = tempValue;
                }
            }
        }
        return randomNumbers;
    }


    private static void playBingoGameManual() {
        boolean[][] bingoGame = new boolean[bingoScoreCard.length][LENGHT_OF_BOARD];
        int[] numbers = readNumbersFromInput(LENGHT_OF_BOARD);
    }

    private static int[] readNumbersFromInput(int lenghtOfBoard) {
        // int[] numbers = new int[LENGHT_OF_BOARD]{2,2,2,2,2};
        int[] numbers = new int[]{2,2,2,2,2};
        return numbers;
    }

    // ---------------------------------------------------------------
    // =========     Método para tratar entrada     ==================
    // ---------------------------------------------------------------

    public static byte pauseToReadKey(String message) {
        byte keyIn = 0;
        System.out.println();
        System.out.print(message);
        try {
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(System.in));
            keyIn = Byte.parseByte(bReader.readLine());
            return keyIn;
        } catch (Exception e) {
            keyIn = 'X';
        } finally {
            return keyIn;
        }
    }

    // ---------------------------------------------------------------
    // =========  Métodos para INICIALIZAR o jogo   ==================
    // ---------------------------------------------------------------
    private static void generateBoardCard() {
        Scanner readFromIn = new Scanner(System.in);
        System.out.println("b) A geração das cartelas será: (M)anual  (A)leatória (X para sair)? ");
        String lineInput = readFromIn.nextLine();
        switch (lineInput.toUpperCase()) {
            case "M":
                genCardFromInput();
                break;
            case "A":
                genRandomCard();
                break;
            case "X":
                System.out.printf("\n !!!   Saindo ... !!! \n\n");
                System.exit(0);
            default:
                System.out.printf("\n !!! Entrada inválida. Saindo ... !!! \n\n");
                System.exit(-1);
        }
    }

    private static void genRandomCard() {
        bingoScoreCard = new int[numPlayers][LENGHT_OF_BOARD];
        int[] numbers;

        for (int nPlayer = 0; nPlayer < numPlayers; nPlayer++) {
            numbers = genRandomInOrderForCards();
            bingoScoreCard[nPlayer] = numbers;
        }
    }

    // Métodos diferentes para geração números aleatórios
    //   Aleatoriedade para CARTELA: executado somente uma única vez
    private static int[] genRandomInOrderForCards() {
        int[] randomNumbers = new int[LENGHT_OF_BOARD];

        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = 0;

        int numRandom = 0;

        // Obter números aleatórios e checar unicidade dos valores
        boolean success = true;
        for (int i = 0; i < LENGHT_OF_BOARD; i++) {
            // Para gerar números entre 1 e 60 (nextInt(1,60+1)
            numRandom = randomForCards.nextInt(SPINNER_MIN_NUMBER, SPINNER_MAX_NUMBER + 1);

            // Para checar repetição de números:
            //  false: número já dentro do array; true: número ainda não incluído
            success = checkUniquenessValueInArray(randomNumbers, numRandom);
            if (success)
                randomNumbers[i] = numRandom;
            else
                i = i - 1;
        }

        // Colocar os números do array em ordem crescente
        int minValue = SPINNER_MAX_NUMBER;
        int[] randomNumbersInOrder = new int[LENGHT_OF_BOARD];;
        int indexMinValue = 0;
        int tempValue = 0;
        for (int i = 0; i < LENGHT_OF_BOARD; i++) {
            for (int j = 0; j < LENGHT_OF_BOARD-1; j++) {
                if (randomNumbers[j+1] < randomNumbers[j]) {
                    tempValue = randomNumbers[j];
                    randomNumbers[j] = randomNumbers[j+1];
                    randomNumbers[j+1] = tempValue;
                }
            }
        }
        return randomNumbers;
    }

    private static boolean checkUniquenessValueInArray(int[] randomNumbers, int numRandom) {
        for (int i = 0; i < randomNumbers.length; i++) {
            if (randomNumbers[i] == numRandom)
                return false;
        }
        return true;
    }


    private static void genCardFromInput() {
        bingoScoreCard = new int[numPlayers][LENGHT_OF_BOARD];
        String[] boardCards = new String[numPlayers];

        Scanner readFromIn = new Scanner(System.in);
        String lineInput = null;
        boolean invalidInput = true;
        while (invalidInput) {
            System.out.printf("b) Entre com %d números para %d cartela(s) separados por hifen, da seguinte forma:\n",
                    LENGHT_OF_BOARD, numPlayers);
            System.out.println("   Exemplo para 3 jogadores: 1,2,3,4,5-6,7,8,9,1-2,3,4,5,6");
            try {
                lineInput = readFromIn.nextLine();
            } catch (Exception e) {
                System.out.printf("\n !!! Entrada inválida. !!! \n\n");
                readFromIn.nextLine();
            }

            // Checar se há separadores para cartelas (jogadores)
            assert lineInput != null;
            if (lineInput.contains("-")) {
                invalidInput = false;
                boardCards = lineInput.split("-");
            } else {
                invalidInput = false;
                boardCards = new String[]{lineInput};
            }

            // Testar a quantidade de cartelas fornecidas
            if (boardCards.length != numPlayers) {
                System.out.printf("\n !!! Entrada de quantidade de cartelas incorreta. !!! \n\n");
                invalidInput = true;
                continue;
            }

            // Para cada cartela, extrair números e testar (1 até 60)
            int numOfPlayer = 0;

            for (String s: boardCards) {
                System.out.println(s);

                String[] card = s.split(",");
                if (card.length != LENGHT_OF_BOARD) {
                    System.out.printf("\n !!! Entrada de quantidade de números incorreta. !!! \n\n");
                    invalidInput = true;
                    break;
                }

                System.out.println(s);

                int numValue = 0;  // O número a ser incluído na cartela
                int cardNumber = 0;  // Índice da cartela que irá receber o valor do número
                for (String numS : card) {
                    try {
                        numValue = Integer.parseInt(numS);
                        if (numValue < SPINNER_MIN_NUMBER || numValue > SPINNER_MAX_NUMBER) {
                            System.out.printf("\n !!! Entrada de número para cartela inválida. !!! \n\n");
                            invalidInput = true;
                            break;
                        }
                    }
                    catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        invalidInput = true;
                    }
                    bingoScoreCard[numOfPlayer][cardNumber++] = numValue;
                }
                numOfPlayer++;  // Vai para a cartela do próximo jogador
            }
        }
    }

    private static void readPlayersFromInput() {
        Scanner readFromIn = new Scanner(System.in);
        String lineInput = null;

        boolean invalidInput = true;
        while (invalidInput) {
            System.out.println("a) Entre com o nome dos jogadores na forma: player1-player2 ou (X) para sair: ");
            try {
                lineInput = readFromIn.nextLine();
            } catch (Exception e) {
                System.out.printf("\n !!! Entrada inválida. !!! \n\n");
                readFromIn.nextLine();
                invalidInput = true;
            }
            assert lineInput != null;
            if (lineInput.equalsIgnoreCase("x")) {
                System.out.println("\n !!! Saindo. !!! \n\n");
                System.exit(0);
            }
            bingoCardPlayers = lineInput.split("-");
            numPlayers = bingoCardPlayers.length;
            invalidInput = false;
        }
    }
    // ---------------------------------------------------------------
    // =========  Métodos para IMPRESSÃO   ==================
    // ---------------------------------------------------------------
    private static void printHeaderForResults() {
        System.out.println();
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println(" $        Resultados        $");
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println();
    }
    private static void printFoundBingoWinner() {
        System.out.println();
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println(" $    BINGO BINGO BINGO    $");
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println();
    }
    private static void printNumbers(int[] numbers) {
        System.out.println();
        System.out.print("Números Sorteados: ");
        for (int i = 0; i < numbers.length; i++)
            System.out.print(numbers[i]+" ");
        System.out.println();
    }
    private static void printGame() {
        for (int i = 0; i < bingoScoreCardHITS.length; i++)
            System.out.printf("\nNúmero Jogador: %d - Nome: %s: %s", i, bingoCardPlayers[i],
                    Arrays.toString(bingoScoreCardHITS[i]));
        System.out.println();
    }
    private static void printBoardCard() {
        for (int i = 0; i < bingoScoreCard.length; i++)
            System.out.printf("\nNúmero Jogador: %d - Nome: %s: %s", i, bingoCardPlayers[i],
                    Arrays.toString(bingoScoreCard[i]));
    }

    private static void printHeaderForBingo(){
        System.out.println();
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println(" ====  B ---------  O   =====  ");
        System.out.println(" ====   I -------- G    =====  ");
        System.out.println(" ====    N ------ N     =====  ");
        System.out.println(" ====     G ---- I      =====  ");
        System.out.println(" ====      O -- B       =====  ");
        System.out.println(" $$$$ Bingo Ada Tech +50 $$$$  ");
        System.out.println();
        System.out.println(" >>>>  Inicialização:  ");
        System.out.println("a) Entrada de usuários na forma jogador1-jogador2-jogador3.");
        System.out.println("b) Escolha da forma de geração das cartelas: manual ou aleatório.");
        System.out.println(" >>>>  Sorteio dos números:  ");
        System.out.println("a) Forma de sorteio de números: manual ou aleatório. ");
        System.out.println("b) Para sair do jogo, pressionar X durante leitura de dados. ");
        System.out.println();
    }
}
