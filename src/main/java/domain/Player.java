package domain;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
    }
}
