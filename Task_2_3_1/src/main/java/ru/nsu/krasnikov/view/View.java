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
import java.awt.Point;
import java.util.List;
import ru.nsu.krasnikov.SnakeConfig;

/**
 * Game view.
 */
public class View {
    private static final String FOOD_IMAGE = "pumpkin.png";
    private static final String SNAKE_HEAD_IMAGE = "clown.png";

    private final int width;
    private final int height;
    private final int rows;
    private final int columns;
    private final int squareSize;

    private final Popup helpWindow;
    private final Stage stage;
    private GraphicsContext graphicsContext;
    private final Image foodImage = new Image(FOOD_IMAGE);
    private final Image snakeHeadImage = new Image(SNAKE_HEAD_IMAGE);

    /**
     * Initialize game view.
     *
     * @param primaryStage game stage.
     * @param snakeConfig  config with game settings.
     */
    public View(Stage primaryStage, SnakeConfig snakeConfig) {
        this.width = snakeConfig.getWidth();
        this.height = snakeConfig.getHeight();
        this.rows = snakeConfig.getRows();
        this.columns = snakeConfig.getColumns();
        this.squareSize = this.width / this.rows;
        this.stage = primaryStage;
        this.helpWindow = new Popup();

        configureHelpWindow();
        configureView();
    }

    private void configureView() {
        stage.setTitle("Snake Game");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
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
                    
                Press any button to start game
                """);
        helpLabel.setFont(new Font("Digital-7", 40));
        helpWindow.getContent().add(helpLabel);
    }

    /**
     * Set key handler.
     *
     * @param eventHandler key handler.
     */
    public void setKeysHandler(EventHandler<KeyEvent> eventHandler) {
        stage.getScene().setOnKeyPressed(eventHandler);
    }

    /**
     * Draw current score.
     *
     * @param score current score.
     */
    public void drawScore(int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }

    /**
     * Draw score need to win.
     *
     * @param score score need to win.
     */
    public void drawScoreToWin(int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Left to win: " + score, 10, 80);
    }

    /**
     * Draw snake.
     *
     * @param snakeBody list of points where snake is situated now.
     */
    public void drawSnake(List<Point> snakeBody) {
        graphicsContext.setFill(Color.web("97E180"));
        for (Point snakeElement : snakeBody) {
            graphicsContext.drawImage(
                    snakeHeadImage,
                    squareSize * snakeElement.getX(),
                    squareSize * snakeElement.getY(),
                    squareSize - 1,
                    squareSize - 1);
        }
    }

    /**
     * Draw walls.
     *
     * @param walls list of walls from config file.
     */
    public void drawWalls(List<Point> walls) {
        graphicsContext.setFill(Color.web("4C4C4F"));
        walls.forEach(wall -> graphicsContext.fillRect(
                squareSize * wall.getX(),
                squareSize * wall.getY(),
                squareSize,
                squareSize
        ));
    }

    /**
     * Draw background.
     */
    public void drawBackground() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.web("9380E1"));
                } else {
                    graphicsContext.setFill(Color.web("7CA7E1"));
                }
                graphicsContext.fillRect(
                        squareSize * i,
                        squareSize * j,
                        squareSize,
                        squareSize);
            }
        }
    }

    /**
     * Draw food.
     *
     * @param foodCoordinates list of points where food is situated now.
     */
    public void drawFood(List<Point> foodCoordinates) {
        for (Point food : foodCoordinates) {
            graphicsContext.drawImage(
                    foodImage,
                    squareSize * food.getX(),
                    squareSize * food.getY(),
                    squareSize,
                    squareSize
            );
        }
    }

    /**
     * Draw that the player won.
     */
    public void drawWin() {
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("You won", width / 3.5, height / 2);
    }

    /**
     * Draw that the player lost.
     */
    public void drawLose() {
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("Game over", width / 3.5, height / 2);
    }

    /**
     * Draw help window.
     */
    public void openHelp() {
        graphicsContext.setFill(Color.web("9380E1"));
        graphicsContext.fillRect(0, 0, width, height);
        helpWindow.show(stage);
    }

    /**
     * Close help window.
     */
    public void closeHelp() {
        helpWindow.hide();
    }

    /**
     * Close game.
     */
    public void close() {
        stage.close();
    }

    /**
     * Draw field with background, snake, food and walls.
     *
     * @param snakeBody       list of points where snake is situated now.
     * @param foodCoordinates list of points where food is situated now.
     * @param walls           list of walls from config file.
     * @param score           current score.
     * @param scoreNeedForWin score need to win.
     */
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
