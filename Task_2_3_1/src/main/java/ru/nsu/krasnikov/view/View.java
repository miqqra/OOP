package ru.nsu.krasnikov.view;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ru.nsu.krasnikov.SnakeConfig;

import java.awt.*;
import java.util.List;

public class View {
    private static final String FOOD_IMAGE = "pumpkin.png";
    private static final String SNAKE_HEAD_IMAGE = "clown.png";

    private final int WIDTH;
    private final int HEIGHT;
    private final int ROWS;
    private final int COLUMNS;
    private final int SQUARE_SIZE;

    private final Popup helpWindow;
    private final Stage stage;
    private GraphicsContext graphicsContext;
    private final Image foodImage = new Image(FOOD_IMAGE);
    private final Image snakeHeadImage = new Image(SNAKE_HEAD_IMAGE);

    public View(Stage primaryStage, SnakeConfig snakeConfig) {
        this.WIDTH = snakeConfig.getWidth();
        this.HEIGHT = snakeConfig.getHeight();
        this.ROWS = snakeConfig.getRows();
        this.COLUMNS = snakeConfig.getColumns();
        this.SQUARE_SIZE = this.WIDTH / this.ROWS;
        this.stage = primaryStage;
        this.helpWindow = new Popup();

        configureHelpWindow();
        configureView();
    }

    private void configureView() {
        stage.setTitle("Snake Game");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        graphicsContext = canvas.getGraphicsContext2D();
        stage.show();
    }

    private void configureHelpWindow() {
        Label helpLabel = new Label("""
                Use arrows or WASD for moving
                Use SPACE or ESC for pause the game
                USE R key to restart the game
                USE H key to open HELP again
                GL
                    
                Press any button to start game
                """);
        helpLabel.setFont(new Font("Digital-7", 40));
        helpWindow.getContent().add(helpLabel);
    }

    public void setKeysHandler(EventHandler<KeyEvent> eventHandler) {
        stage.getScene().setOnKeyPressed(eventHandler);
    }

    public void drawScore(int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }

    public void drawScoreToWin(int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Left to win: " + score, 10, 80);
    }

    public void drawSnake(List<Point> snakeBody) {
        graphicsContext.setFill(Color.web("97E180"));
        for (Point snakeElement : snakeBody) {
            graphicsContext.drawImage(
                    snakeHeadImage,
                    SQUARE_SIZE * snakeElement.getX(),
                    SQUARE_SIZE * snakeElement.getY(),
                    SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1);
        }
    }

    public void drawWalls(List<Point> walls) {
        graphicsContext.setFill(Color.web("4C4C4F"));
        walls.forEach(wall -> graphicsContext.fillRect(
                SQUARE_SIZE * wall.getX(),
                SQUARE_SIZE * wall.getY(),
                SQUARE_SIZE,
                SQUARE_SIZE
        ));
    }

    public void drawBackground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.web("9380E1"));
                } else {
                    graphicsContext.setFill(Color.web("7CA7E1"));
                }
                graphicsContext.fillRect(
                        SQUARE_SIZE * i,
                        SQUARE_SIZE * j,
                        SQUARE_SIZE,
                        SQUARE_SIZE);
            }
        }
    }

    public void drawFood(List<Point> foodCoordinates) {
        for (Point food : foodCoordinates) {
            graphicsContext.drawImage(
                    foodImage,
                    SQUARE_SIZE * food.getX(),
                    SQUARE_SIZE * food.getY(),
                    SQUARE_SIZE,
                    SQUARE_SIZE
            );
        }
    }

    public void drawWin() {
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("You win", WIDTH / 3.5, HEIGHT / 2);
    }

    public void drawLose() {
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("Game over", WIDTH / 3.5, HEIGHT / 2);
    }

    public void openHelp() {
        graphicsContext.setFill(Color.web("9380E1"));
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
        helpWindow.show(stage);
    }

    public void closeHelp() {
        helpWindow.hide();
    }

    public void close() {
        stage.close();
    }

    public void drawField(List<Point> snakeBody,
                          List<Point> foodCoordinates,
                          List<Point> walls,
                          int score,
                          int scoreNeedForWin) {
        drawBackground();
        drawFood(foodCoordinates);
        drawWalls(walls);
        drawSnake(snakeBody);
        drawScore(score);
        drawScoreToWin(scoreNeedForWin - score);
    }
}
