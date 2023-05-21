package ru.nsu.krasnikov;

import ru.nsu.krasnikov.dto.Pizza;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
    private final Pizzeria pizzeria;
    private final Storage storage;
    private final String chefName;
    private final int experience; //inversely proportional to cooking time

    public Chef(Pizzeria pizzeria,
                Storage storage,
                String chefName,
                int experience) {
        this.pizzeria = pizzeria;
        this.storage = storage;
        this.chefName = chefName;
        this.experience = experience;
    }


    public Pizza takeOrder() {
        Pizza newOrder;
        synchronized (pizzeria) {
            newOrder = pizzeria.getNewOrder();
        }

        if (newOrder == null) {
            Logger.noNewOrders();
            storage.runFlag = false;
        } else {
            Logger.chefGotNewOrder(chefName, newOrder.name());
            try {
                TimeUnit.SECONDS.sleep(experience);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return newOrder;
    }

    public Pizza produce() {
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

    @Override
    public void run() {
        while (true) {
            if (produce() == null) {
                break;
            }
        }
    }
}
