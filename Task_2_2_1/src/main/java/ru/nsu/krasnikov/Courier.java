package ru.nsu.krasnikov;

import java.util.concurrent.TimeUnit;
import java.util.List;
import ru.nsu.krasnikov.dto.Pizza;

/**
 * Courier-consumer class.
 */
public class Courier implements Runnable {
    private final Pizzeria pizzeria;
    private final Storage storage;
    private final String courierName;
    private final int trunkSize;
    private final int speed;

    /**
     * Courier-consumer constructor.
     *
     * @param pizzeria    pizzeria.
     * @param storage     storage.
     * @param courierName name.
     * @param trunkSize   size of couriers trunk.
     * @param speed       speed, inversely proportional to delivery time.
     */
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

    /**
     * Delivers a pizza.
     *
     * @throws InterruptedException thread sleep error.
     */
    public void deliver() throws InterruptedException {
        while (storage.runFlag || !storage.isEmpty()) {
            while (storage.isEmpty() && storage.runFlag) {
                Logger.emptyStorage();
                storage.waitOnEmptyStorage();
            }
            takePizza();
        }
    }

    /**
     * Thread run function.
     */
    @Override
    public void run() {
        while (!pizzeria.isEmpty()) {
            try {
                deliver();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
