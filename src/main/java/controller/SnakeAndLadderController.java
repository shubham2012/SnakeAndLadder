package controller;

import dao.Board;
import dao.Ladder;
import dao.Players;
import dao.Snake;
import domain.Player;
import exception.WrongInputException;
import utils.Dice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakeAndLadderController {

    private static Integer DEFAULT_BOARD_SIZE = 100;
    private static Integer DEFAULT_DICE_SIZE = 6;

    public static void main(String[] args) throws WrongInputException {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(DEFAULT_BOARD_SIZE);
        Dice dice = new Dice(DEFAULT_DICE_SIZE);
        Players players = new Players(2);
        players.add(new Player("Ramesh"));
        players.add(new Player("Suresh"));
        Snake snake = new Snake();
        Ladder ladder = new Ladder();
        Integer numberOfSnakes = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < numberOfSnakes; i++) {
            String[] split = scanner.nextLine().split(" ");
            snake.add(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        }
        Integer numberOfLadder = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < numberOfLadder; i++) {
            String[] split = scanner.nextLine().split(" ");
            ladder.add(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        }

        Integer numberOfPlayers = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < numberOfPlayers; i++) {
            String player = scanner.nextLine();
            players.add(new Player(player));
        }
        SnakeAndLadderController controller = new SnakeAndLadderController();
        Queue<Player> result = controller.startGame(board, dice, ladder, snake, players);
    }

    /**
     * Start the game and return the result finally
     *
     * @param board
     * @param dice
     * @param ladder
     * @param snake
     * @param players
     * @return
     */
    public Queue<Player> startGame(Board board, Dice dice, Ladder ladder, Snake snake, Players players) {
        Queue<Player> result = new LinkedList<>();
        while (players.size() > 1) {
            result = movePlayer(board, dice, ladder, snake, players, result);
        }
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Player player = result.poll();
            System.out.println(String.format("%s won with position %d", player.getName(), (i + 1)));
        }
        return result;
    }

    /**
     * Move player by rolling dice
     *
     * @param board
     * @param dice
     * @param ladder
     * @param snake
     * @param players
     * @param result
     * @return
     */
    private Queue<Player> movePlayer(Board board, Dice dice, Ladder ladder, Snake snake, Players players, Queue<Player> result) {
        // check if there is only 1 player remaining
        if (players.size() <= 1)
            return result;
        int roll = dice.roll();
        Player player = players.get();
        int initialPos = player.getCurrentPosition();
        // If initial position is of board size then that player won the game
        // so add it to result and return
        if (initialPos == board.getSize()) {
            Player playerWon = players.remove();
            result.add(playerWon);
            return result;
        }
        // If initialPos + roll > board size then this time player will not play and will be ideal
        else if (initialPos + roll > board.getSize()) {
            players.removeAndPush();
            return result;
        }
        // initialPos + roll == board.getSize() then player won the game
        else if (initialPos + roll == board.getSize()) {
            Player playerWon = players.remove();
            result.add(playerWon);
        }
        player.setCurrentPosition(initialPos + roll);
        // process snake and ladder recursively if exists at the destination location
        processForSnakeAndLadderTour(player, ladder, snake);
        System.out.println(String.format("%s rolled a %d and moved from %d to %d",
                player.getName(), roll, initialPos, player.getCurrentPosition()));
        // If dice came as 6 then will have a second chance for the player
        if (roll == DEFAULT_DICE_SIZE || roll == DEFAULT_DICE_SIZE * 2)
            return result;
        else
            players.removeAndPush();
        return result;
    }

    /**
     * Process snake and ladder recursively
     *
     * @param player
     * @param ladder
     * @param snake
     */
    private void processForSnakeAndLadderTour(Player player, Ladder ladder, Snake snake) {
        int currentPosition = player.getCurrentPosition();
        if (snake.getSnake(currentPosition) != -1) {
            player.setCurrentPosition(snake.getSnake(currentPosition));
            processForSnakeAndLadderTour(player, ladder, snake);
        } else if (ladder.getLadder(currentPosition) != -1) {
            player.setCurrentPosition(ladder.getLadder(currentPosition));
            processForSnakeAndLadderTour(player, ladder, snake);
        }
    }

}
