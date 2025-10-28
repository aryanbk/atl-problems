package snakeGame;

import snakeGame.enums.Direction;
import snakeGame.pojos.Cell;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SnakeGameImp implements SnakeGame {
    Deque<Cell> snake = new LinkedList<>();
    Set<Cell> cells = new HashSet<>();

    @Override
    public void moveSnake(Direction direction) {

    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
