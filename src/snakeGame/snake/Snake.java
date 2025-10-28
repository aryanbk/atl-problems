package snakeGame.snake;

import snakeGame.enums.Direction;

public interface Snake {
    void moveSnake(Direction direction);

    void scaleUp(Direction direction);
}
