package controller;

import dao.Board;
import dao.Ladder;
import dao.Players;
import dao.Snake;
import domain.Player;
import exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Dice;

class SnakeAndLadderControllerTest {

    private SnakeAndLadderController controller = new SnakeAndLadderController();
    private Snake snake = new Snake();
    private Ladder ladder = new Ladder();

    @BeforeEach
    void setUp() throws WrongInputException {
        snake.add(62, 5);
        snake.add(33, 6);
        snake.add(49, 9);
        snake.add(88, 16);
        snake.add(41, 20);
        snake.add(56, 53);
        snake.add(98, 64);
        snake.add(93, 73);
        snake.add(95, 75);

        ladder.add(2, 37);
        ladder.add(27, 46);
        ladder.add(10, 32);
        ladder.add(51, 68);
        ladder.add(61, 79);
        ladder.add(65, 84);
        ladder.add(71, 91);
        ladder.add(81, 100);
    }

    @Test
    void test_start_game_with_2_players() throws WrongInputException {
        Board board = new Board(100);
        Dice dice = new Dice(6);
        Players players = new Players(2);
        players.add(new Player("Ramesh"));
        players.add(new Player("Suresh"));

        controller.startGame(board, dice, ladder, snake, players);
    }

    @Test
    void test_start_game_with_4_players() throws WrongInputException {
        Board board = new Board(100);
        Dice dice = new Dice(6);
        Players players = new Players(4);
        players.add(new Player("Ramesh"));
        players.add(new Player("Suresh"));
        players.add(new Player("Kamlesh"));
        players.add(new Player("Nilesh"));

        controller.startGame(board, dice, ladder, snake, players);
    }

    @Test
    void test_start_game_with_4_players_with_2_dice() throws WrongInputException {
        Board board = new Board(100);
        Dice dice = new Dice(12);
        Players players = new Players(4);
        players.add(new Player("Ramesh"));
        players.add(new Player("Suresh"));
        players.add(new Player("Kamlesh"));
        players.add(new Player("Nilesh"));

        controller.startGame(board, dice, ladder, snake, players);
    }

}