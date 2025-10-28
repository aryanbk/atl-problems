package snakeGame.snake;

import snakeGame.enums.Direction;
import snakeGame.pojos.Cell;

import java.util.*;

public class SnakeImp implements Snake {
    Deque<Cell> snake = new LinkedList<>();
    Set<Cell> cells = new HashSet<>();
    // Cell head =

    @Override
    public void moveSnake(Direction direction) {
        // snake.offer()
    }

    @Override
    public void scaleUp(Direction direction) {

    }
}
