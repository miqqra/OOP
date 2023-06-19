package ru.nsu.krasnikov.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ru.nsu.krasnikov.model.Model;
import ru.nsu.krasnikov.view.View;

/**
 * Game controller.
 */
public class Controller {
    private final View view;
    private final Model model;
    private boolean paused = false;
    private boolean helpIsOpened = false;
    private boolean noMoves = true;
    private Direction direction;
    private final Timeline timeline;

    /**
     * Initialize controller.
     *
     * @param view  game view.
     * @param model game model.
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        view.setKeysHandler(new KeyHandler(this));
        timeline = new Timeline(new KeyFrame(
                Duration.millis(130),
                e -> {
                    if (model.win()) {
                        view.drawWin();
                    } else if (model.gameOver()) {
                        view.drawLose();
                    } else if (!noMoves) {
                        tick();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void tick() {
        if (!noMoves) {
            model.eatFood();
            model.moveSnake(direction);
        }
    }

    /**
     * Change snake's direction, new direction cannot be opposite with previous one.
     *
     * @param direction new direction.
     */
    public void setDirection(Direction direction) {
        if (noMoves || !checkOppositeDirection(this.direction, direction)) {
            noMoves = false;
            this.direction = direction;
        }
    }

    private boolean checkOppositeDirection(Direction currentDirection, Direction newDirection) {
        return (currentDirection.equals(Direction.UP)
                && newDirection.equals(Direction.DOWN))
                || (currentDirection.equals(Direction.DOWN)
                && newDirection.equals(Direction.UP))
                || (currentDirection.equals(Direction.LEFT)
                && newDirection.equals(Direction.RIGHT))
                || (currentDirection.equals(Direction.RIGHT)
                && newDirection.equals(Direction.LEFT));
    }

    /**
     * Pause the game.
     */
    public void pause() {
        if (paused) {
            timeline.play();
        } else {
            timeline.pause();
        }
        paused = !paused;
    }

    /**
     * Restart the game.
     */
    public void restartGame() {
        paused = false;
        noMoves = true;
        direction = null;
        model.restartGame();
    }

    /**
     * Show help window and hide it if it was opened.
     */
    public void help() {
        if (!helpIsOpened) {
            timeline.pause();
            view.openHelp();
        } else {
            timeline.play();
            view.closeHelp();
        }
        helpIsOpened = !helpIsOpened;
    }

    /**
     * Check if help window opened.
     *
     * @return true if opened, false otherwise.
     */
    public boolean isHelpIsOpened() {
        return helpIsOpened;
    }

    /**
     * Close the game.
     */
    public void closeGame() {
        view.close();
    }
}
