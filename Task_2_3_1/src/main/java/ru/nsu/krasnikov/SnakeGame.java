package ru.nsu.krasnikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.krasnikov.controller.Controller;
import ru.nsu.krasnikov.model.Model;
import ru.nsu.krasnikov.view.View;

/**
 * Snake game.
 */
public class SnakeGame extends Application {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        SnakeConfig snakeConfig = objectMapper.readValue(
                new File(ClassLoader
                        .getSystemResource("config.json")
                        .getFile()),
                SnakeConfig.class);
        View view = new View(primaryStage, snakeConfig);
        Model model = new Model(view, snakeConfig);
        Controller controller = new Controller(view, model);
        controller.help();
    }

    /**
     * Game start points.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
