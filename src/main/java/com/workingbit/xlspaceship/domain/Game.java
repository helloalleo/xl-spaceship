package com.workingbit.xlspaceship.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Aleksey Popryaduhin on 11:42 24/07/2017.
 */
@Data
public class Game {

    private EnumRule rule;
    private Board playerBoard;
    private Board opponentBoard;
    private User playerTurn;

    public Game(User player, User opponent, String rules) {
        this.rule = EnumRule.fromString(rules);
        playerBoard = new Board(player);
        opponentBoard = new Board(opponent);
        playerTurn = player;
    }

    public List<String> getPlayerBoardAsList() {
        return playerBoard.getBoardAsList(false);
    }

    public List<String> getOpponentBoardAsList() {
        return opponentBoard.getBoardAsList(true);
    }

    @Override
    public String toString() {
        return "Game{" +
                "playerBoard=" + playerBoard.getPlayer().getUserId() +
                "opponentBoard=" + opponentBoard.getPlayer().getUserId() +
                '}';
    }

    public void placeShips() {
        playerBoard.randomPlaceShips();
        opponentBoard.randomPlaceShips();
        // cross linking ship's count
        playerBoard.opponent = opponentBoard;
        opponentBoard.opponent = playerBoard;
    }
}
