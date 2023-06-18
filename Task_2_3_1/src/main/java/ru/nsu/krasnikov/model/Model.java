package ru.nsu.krasnikov.model;

import ru.nsu.krasnikov.SnakeConfig;
import ru.nsu.krasnikov.view.View;
import ru.nsu.krasnikov.controller.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final int WIDTH;
    private final int HEIGHT;
    private final int SQUARE_SIZE;
    private final int COLUMNS;
    private final int ROWS;

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

    public Model(View view, SnakeConfig snakeConfig) {
        this.initialSnakePosition = snakeConfig.getSnakePlace();
        this.view = view;
        this.walls = snakeConfig.getWalls();
        this.startLength = snakeConfig.getStartLength();
        this.foodNumber = snakeConfig.getFoodNumber();
        this.scoreNeedForWin = snakeConfig.getScoreNeedForWin();
        this.WIDTH = snakeConfig.getWidth();
        this.ROWS = snakeConfig.getRows();
        this.HEIGHT = snakeConfig.getHeight();
        this.COLUMNS = snakeConfig.getColumns();
        this.SQUARE_SIZE = this.WIDTH / this.ROWS;

        this.snakeBody.addAll(snakeConfig.getSnakePlace());
        this.foodCoordinates = new ArrayList<>();
        this.score = this.startLength;

        initSnake();
    }

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
            Point food = new Point((int) (Math.random() * ROWS), (int) (Math.random() * COLUMNS));
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

    public boolean gameOver() {
        return checkBorderCollision(snakeHead)
                || anyEquals(snakeHead, snakeBody.subList(1, snakeBody.size()))
                || anyEquals(snakeHead, walls);
    }

    private boolean checkBorderCollision(Point snakeHead) {
        return snakeHead.x < 0
                || snakeHead.y < 0
                || snakeHead.x * SQUARE_SIZE >= WIDTH
                || snakeHead.y * SQUARE_SIZE >= HEIGHT;
    }

    public boolean win() {
        return snakeBody.size() == scoreNeedForWin;
    }

    public void restartGame() {
        snakeBody.clear();
        snakeBody.addAll(initialSnakePosition);
        foodCoordinates.clear();
        initSnake();
        view.drawField(snakeBody, foodCoordinates, walls, score, scoreNeedForWin);
    }

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
