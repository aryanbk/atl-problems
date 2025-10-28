package snakeGame;

import snakeGame.enums.Direction;

public interface SnakeGame {

    void moveSnake(Direction direction);

    boolean isGameOver();
}
