package ru.nsu.krasnikov;

import java.awt.*;
import java.util.List;

/**
 * Game settings.
 */
public class SnakeConfig {
    int width;
    int height;
    int rows;
    int columns;
    int startLength;
    int foodNumber;
    int scoreNeedForWin;
    List<Point> walls;
    List<Point> snakePlace;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStartLength() {
        return startLength;
    }

    public void setStartLength(int startLength) {
        this.startLength = startLength;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }

    public int getScoreNeedForWin() {
        return scoreNeedForWin;
    }

    public void setScoreNeedForWin(int scoreNeedForWin) {
        this.scoreNeedForWin = scoreNeedForWin;
    }

    public List<Point> getWalls() {
        return walls;
    }

    public void setWalls(List<Point> walls) {
        this.walls = walls;
    }

    public List<Point> getSnakePlace() {
        return snakePlace;
    }

    public void setSnakePlace(List<Point> snakePlace) {
        this.snakePlace = snakePlace;
    }
}
