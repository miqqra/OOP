package ru.nsu.krasnikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PizzeriaTest {
    @Test
    public void pizzeriaInfoTest() {
        File pizzas =
                new File(ClassLoader
                        .getSystemResource("pizzas.json")
                        .getFile());
        File pizzeriaInfo =
                new File(ClassLoader
                        .getSystemResource("pizzeria.json")
                        .getFile());

        Pizzeria pizzeria = new Pizzeria(pizzeriaInfo, pizzas);
        assertEquals(pizzeria.getChefs().size(), 3);
        assertEquals(pizzeria.getCouriers().size(), 2);
        assertEquals(pizzeria.getStorage().getMaxCapacity(), 5);
        assertEquals(pizzeria.getOrders().size(), 8);
    }

    @Test
    public void pizzeriaTest() {
        File pizzas =
                new File(ClassLoader
                        .getSystemResource("pizzas.json")
                        .getFile());
        File pizzeriaInfo =
                new File(ClassLoader
                        .getSystemResource("pizzeria.json")
                        .getFile());

        Pizzeria pizzeria = new Pizzeria(pizzeriaInfo, pizzas);
        Thread mainThread = new Thread(pizzeria);
        mainThread.start();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(pizzeria.isEmpty());
        assertTrue(pizzeria.getStorage().getReadyPizzas().isEmpty());
    }
}
