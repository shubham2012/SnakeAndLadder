package dao;

import domain.Player;
import exception.WrongInputException;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class Players {

    private static Queue<Player> players;
    private int numberOfPlayers;

    public Players(int numberOfPlayers) {
        this.players = new LinkedList<>();
        this.numberOfPlayers = numberOfPlayers;
    }

    public Queue<Player> getAll() {
        return players;
    }

    public Integer size(){
        return players.size();
    }

    public void add(Player player) throws WrongInputException {
        if (players.size() >= numberOfPlayers)
            throw new WrongInputException(0, "You can not add more players. Max number of players exceeded");
        players.add(player);
    }

    public Player get(){
        return players.peek();
    }

    public Player remove(){
        return players.poll();
    }

    public void removeAndPush(){
        Player poll = players.poll();
        players.add(poll);
    }
}
