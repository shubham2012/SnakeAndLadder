package utils;

import exception.WrongInputException;

import java.util.Random;

public class Dice {

    private static int DEFAULT_SIZE = 6;
    private static int size;

    public Dice(int size) throws WrongInputException {
        if (!(size == 6 || size ==12))
            throw new WrongInputException(0, "Dice can be only of size 6 or 12");
        this.size = size;
    }

    public Dice() {
        this.size = DEFAULT_SIZE; // Default Size
    }

    public int roll() {
        Random random = new Random();
        if (size == DEFAULT_SIZE)
            return random.nextInt(DEFAULT_SIZE) + 1;
        else
            return (random.nextInt(DEFAULT_SIZE) + 1) + (random.nextInt(DEFAULT_SIZE) + 1);
    }

}
