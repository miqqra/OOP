package ru.nsu.krasnikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.krasnikov.controller.Direction;
import ru.nsu.krasnikov.model.Model;

/**
 * Model test.
 */
public class ModelTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void moveTest() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config1.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Model model = new Model(snakeConfig);

        model.moveSnake(Direction.LEFT);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(9, 5));

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(9, 4));

        model.moveSnake(Direction.RIGHT);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 4));

        model.moveSnake(Direction.DOWN);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 5));
    }

    @Test
    public void snakeCorrectness1() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config2.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Model(snakeConfig));
        Assertions.assertEquals(exception.getMessage(),
                "Incorrect snake config: snake elements are not attached to each other\n");
    }

    @Test
    public void snakeCorrectness2() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config3.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Model(snakeConfig));
        Assertions.assertEquals(exception.getMessage(),
                "Incorrect snake config: snake intersects with wall\n");
    }

    @Test
    public void wallCollisionTest() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config1.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Model model = new Model(snakeConfig);

        model.moveSnake(Direction.LEFT);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(9, 5));
        Assertions.assertFalse(model.gameOver());

        //wall
        model.moveSnake(Direction.LEFT);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(8, 5));
        Assertions.assertTrue(model.gameOver());
    }

    @Test
    public void borderCollisionTest() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config1.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Model model = new Model(snakeConfig);

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 4));
        Assertions.assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 3));
        Assertions.assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 2));
        Assertions.assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 1));
        Assertions.assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        Assertions.assertEquals(model.getSnakeBody().get(0), new Point(10, 0));


        model.moveSnake(Direction.UP);
        Assertions.assertTrue(model.gameOver());
    }
}
