package ru.nsu.krasnikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.nsu.krasnikov.controller.Direction;
import ru.nsu.krasnikov.model.Model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

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
        assertEquals(model.getSnakeBody().get(0), new Point(9, 5));

        model.moveSnake(Direction.UP);
        assertEquals(model.getSnakeBody().get(0), new Point(9, 4));

        model.moveSnake(Direction.RIGHT);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 4));

        model.moveSnake(Direction.DOWN);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 5));
    }

    @Test
    public void snakeCorrectness1() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config2.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Model(snakeConfig));
        assertEquals(exception.getMessage(),
                "Incorrect snake config: snake elements are not attached to each other\n");
    }

    @Test
    public void snakeCorrectness2() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config3.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Model(snakeConfig));
        assertEquals(exception.getMessage(),
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
        assertEquals(model.getSnakeBody().get(0), new Point(9, 5));
        assertFalse(model.gameOver());

        //wall
        model.moveSnake(Direction.LEFT);
        assertEquals(model.getSnakeBody().get(0), new Point(8, 5));
        assertTrue(model.gameOver());
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
        assertEquals(model.getSnakeBody().get(0), new Point(10, 4));
        assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 3));
        assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 2));
        assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 1));
        assertFalse(model.gameOver());

        model.moveSnake(Direction.UP);
        assertEquals(model.getSnakeBody().get(0), new Point(10, 0));


        model.moveSnake(Direction.UP);
        assertTrue(model.gameOver());
    }
}
