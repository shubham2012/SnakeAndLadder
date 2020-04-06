package dao;

import exception.WrongInputException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Ladder {

    private static Map<Integer, Integer> ladder;

    public Ladder() {
        this.ladder = new HashMap<>();
    }

    public Integer getLadder(int loc) {
        return ladder.getOrDefault(loc, -1);
    }

    public void add(Integer start, Integer end) throws WrongInputException {
        if (start > end)
            throw new WrongInputException(0,"Ladder start can not be greater then its end");
        ladder.put(start, end);
    }
}
