package ru.nsu.krasnikov.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    private final Controller controller;

    public KeyHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code != KeyCode.H && controller.isHelpIsOpened()) {
            controller.help();
        }

        System.out.println("next");
        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            controller.setDirection(Direction.RIGHT);
        } else if (code == KeyCode.LEFT || code == KeyCode.A) {
            controller.setDirection(Direction.LEFT);
        } else if (code == KeyCode.UP || code == KeyCode.W) {
            controller.setDirection(Direction.UP);
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            controller.setDirection(Direction.DOWN);
        } else if (code == KeyCode.ESCAPE || code == KeyCode.SPACE) {
            controller.pause();
        } else if (code == KeyCode.H) {
            controller.help();
        } else if (code == KeyCode.R) {
            controller.restartGame();
        } else if (code == KeyCode.DELETE) {
            controller.closeGame();
        }
    }
}
