package ru.nsu.krasnikov.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.krasnikov.SnakeConfig;
import ru.nsu.krasnikov.controller.Direction;
import ru.nsu.krasnikov.view.View;

/**
 * Game model.
 */
public class Model {
    private final int width;
    private final int height;
    private final int squareSize;
    private final int columns;
    private final int rows;

    private final List<Point> initialSnakePosition;
    private final View view;
    private final int startLength;
    private final int foodNumber;
    private int score;
    private final int scoreNeedForWin;
    private final List<Point> walls;

    private final List<Point> snakeBody = new ArrayList<>();
    private final List<Point> foodCoordinates;
    private Point prevSnakePosition;
    private Point snakeHead;

    /**
     * Initialize model.
     *
     * @param view        game view.
     * @param snakeConfig game configuration.
     */
    public Model(View view, SnakeConfig snakeConfig) {
        this.initialSnakePosition = snakeConfig.getSnakePlace();
        this.view = view;
        this.walls = snakeConfig.getWalls();
        this.startLength = snakeConfig.getStartLength();
        this.foodNumber = snakeConfig.getFoodNumber();
        this.scoreNeedForWin = snakeConfig.getScoreNeedForWin();
        this.width = snakeConfig.getWidth();
        this.rows = snakeConfig.getRows();
        this.height = snakeConfig.getHeight();
        this.columns = snakeConfig.getColumns();
        this.squareSize = this.width / this.rows;

        this.snakeBody.addAll(snakeConfig.getSnakePlace());
        this.foodCoordinates = new ArrayList<>();
        this.score = this.startLength;

        initSnake();
    }

    /**
     * Move snake.
     *
     * @param direction direction where snake moves.
     */
    public void moveSnake(Direction direction) {
        prevSnakePosition = snakeBody.remove(snakeBody.size() - 1);
        snakeHead = new Point(snakeHead.x, snakeHead.y);
        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        snakeBody.add(0, snakeHead);
        view.drawField(snakeBody, foodCoordinates, walls, score, scoreNeedForWin);
    }

    private void generateFood() {
        while (true) {
            Point food = new Point((int) (Math.random() * rows), (int) (Math.random() * columns));
            if (anyEquals(food, snakeBody)
                    || anyEquals(food, foodCoordinates)
                    || anyEquals(food, walls)) {
                continue;
            }
            foodCoordinates.add(food);
            break;
        }
    }

    private boolean anyEquals(Point p, List<Point> pointList) {
        return pointList
                .stream()
                .anyMatch(
                        point -> point.getX() == p.getX() &&
                                point.getY() == p.getY());
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    /**
     * Check if game over.
     *
     * @return true if player lost, false otherwise.
     */
    public boolean gameOver() {
        return checkBorderCollision(snakeHead)
                || anyEquals(snakeHead, snakeBody.subList(1, snakeBody.size()))
                || anyEquals(snakeHead, walls);
    }

    private boolean checkBorderCollision(Point snakeHead) {
        return snakeHead.x < 0
                || snakeHead.y < 0
                || snakeHead.x * squareSize >= width
                || snakeHead.y * squareSize >= height;
    }

    /**
     * Check if player won.
     *
     * @return true if player won, false otherwise.
     */
    public boolean win() {
        return snakeBody.size() == scoreNeedForWin;
    }

    /**
     * Restart the game, clear all snake and food coordinates, and initialize game again.
     */
    public void restartGame() {
        snakeBody.clear();
        snakeBody.addAll(initialSnakePosition);
        foodCoordinates.clear();
        initSnake();
        view.drawField(snakeBody, foodCoordinates, walls, score, scoreNeedForWin);
    }

    /**
     * Check config's snake correctness and generate new food coordinates.
     */
    public void initSnake() {
        checkSnakeCorrectness();
        while (foodCoordinates.size() < foodNumber) {
            generateFood();
        }
        snakeHead = snakeBody.get(0);
        score = startLength;
    }

    private void checkSnakeCorrectness() {
        Point prevSnakeElement = null;
        for (Point snakeElement : snakeBody) {
            if (prevSnakeElement != null && !pointsAreClose(prevSnakeElement, snakeElement)) {
                throw new IllegalArgumentException("""
                        Incorrect snake config: snake elements are not attached to each other
                        """);
            }
            if (anyEquals(snakeElement, walls)) {
                throw new IllegalArgumentException("""
                        Incorrect snake config: snake intersects with wall
                        """);
            }
            prevSnakeElement = snakeElement;
        }
    }

    private boolean pointsAreClose(Point p1, Point p2) {
        return ((Math.abs(p1.getX() - p2.getX()) == 1) && (p1.getY() - p2.getY() == 0)) ||
                ((Math.abs(p1.getY() - p2.getY()) == 1) && (p1.getX() - p2.getX() == 0));
    }

    /**
     * Check if snake's head is on food coordinate and if it is true, eat it.
     */
    public void eatFood() {
        for (Point food : foodCoordinates) {
            if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
                snakeBody.add(prevSnakePosition);
                foodCoordinates.remove(food);
                generateFood();
                score++;
                return;
            }
        }
    }
}
