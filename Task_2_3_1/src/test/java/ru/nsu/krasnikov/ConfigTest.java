package ru.nsu.krasnikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Game settings test.
 */
public class ConfigTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void configTest() throws IOException {
        File configFile =
                new File(ClassLoader
                        .getSystemResource("config1.json")
                        .getFile());
        SnakeConfig snakeConfig = objectMapper.readValue(configFile, SnakeConfig.class);

        assertEquals(snakeConfig.width, 800);
        assertEquals(snakeConfig.height, 800);
        assertEquals(snakeConfig.rows, 20);
        assertEquals(snakeConfig.columns, 20);
        assertEquals(snakeConfig.startLength, 1);
        assertEquals(snakeConfig.foodNumber, 5);
        assertEquals(snakeConfig.scoreNeedForWin, 50);
        assertEquals(snakeConfig.walls.size(), 3);
        assertEquals(snakeConfig.walls.get(0), new Point(1, 2));
        assertEquals(snakeConfig.snakePlace.size(), 1);
        assertEquals(snakeConfig.snakePlace.get(0), new Point(10, 5));
    }
}
