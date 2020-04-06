package dao;

import lombok.Data;
import lombok.Getter;

@Data
public class Board {

    private static int size;

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
