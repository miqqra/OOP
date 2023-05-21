package ru.nsu.krasnikov;

import ru.nsu.krasnikov.dto.Pizza;
import java.util.concurrent.TimeUnit;

/**
 * Chef-producer class.
 */
public class Chef implements Runnable {
    private final Pizzeria pizzeria;
    private final Storage storage;
    private final String chefName;
    private final int experience;

    /**
     * Chef-producer constructor.
     *
     * @param pizzeria pizzeria.
     * @param storage storage.
     * @param chefName name.
     * @param experience experience, inversely proportional to cooking time.
     */
    public Chef(Pizzeria pizzeria,
                Storage storage,
                String chefName,
                int experience) {
        this.pizzeria = pizzeria;
        this.storage = storage;
        this.chefName = chefName;
        this.experience = experience;
    }

    /**
     * Take a new order.
     *
     * @return new order.
     * @throws InterruptedException thread sleep error.
     */
    public Pizza takeOrder() throws InterruptedException {
        Pizza newOrder;
        synchronized (pizzeria) {
            newOrder = pizzeria.getNewOrder();
        }

        if (newOrder == null) {
            Logger.noNewOrders();
            storage.runFlag = false;
        } else {
            Logger.chefGotNewOrder(chefName, newOrder.name());
            TimeUnit.SECONDS.sleep(experience);
        }
        return newOrder;
    }

    /**
     * Producer a pizza.
     *
     * @return produced pizza.
     * @throws InterruptedException thread sleep error.
     */
    public Pizza produce() throws InterruptedException {
        Pizza newOrder = takeOrder();
        while (storage.isFull() && storage.runFlag) {
            try {
                Logger.fullStorage();
                storage.waitOnFullStorage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (newOrder != null) {
            storage.addPizza(newOrder);
            Logger.chefFinishedOrder(chefName, newOrder.name());
            storage.notifyOnEmptyStorage();
        }
        return newOrder;
    }

    /**
     * Thread run function.
     */
    @Override
    public void run() {
        while (true) {
            try {
                if (produce() == null) {
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
