package ru.nsu.krasnikov;

import ru.nsu.krasnikov.dto.Pizza;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Courier implements Runnable {
    private final Pizzeria pizzeria;
    private final Storage storage;
    private final String courierName;
    private final int trunkSize;
    private final int speed;

    public Courier(Pizzeria pizzeria,
                   Storage storage,
                   String courierName,
                   int trunkSize,
                   int speed) {
        this.pizzeria = pizzeria;
        this.storage = storage;
        this.courierName = courierName;
        this.trunkSize = trunkSize;
        this.speed = speed;
    }

    private void takePizza() {
        List<Pizza> pizzas = storage.takePizza(trunkSize);
        if (pizzas.isEmpty()) {
            return;
        }
        storage.notifyOnFullStorage();
        pizzas.forEach(pizza ->
                Logger.courierTookPizza(courierName, pizza.name()));
        pizzas.forEach(pizza ->
        {
            try {
                TimeUnit.SECONDS.sleep((long) pizza.deliveryTime() * speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Logger.courierDeliveredPizza(courierName, pizza.name());
        });
    }

    public void deliver() {
        while (storage.runFlag || !storage.isEmpty()) {
            while (storage.isEmpty() && storage.runFlag) {
                try {
                    Logger.emptyStorage();
                    storage.waitOnEmptyStorage();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            takePizza();
        }
    }

    @Override
    public void run() {
        while (!pizzeria.isEmpty()) {
            deliver();
        }
    }
}
