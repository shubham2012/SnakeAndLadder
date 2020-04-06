package dao;

import exception.WrongInputException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Snake {

    private static Map<Integer, Integer> snake;

    public Snake() {
        this.snake = new HashMap<>();
    }

    public Integer getSnake(int loc) {
        return snake.getOrDefault(loc, -1);
    }

    public void add(Integer start, Integer end) throws WrongInputException {
        if (start < end)
            throw new WrongInputException(0,"Ladder start can not be greater then its end");
        snake.put(start, end);
    }

}
