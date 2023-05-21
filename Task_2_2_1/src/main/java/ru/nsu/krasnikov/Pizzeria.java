package ru.nsu.krasnikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.krasnikov.dto.OrdersInfo;
import ru.nsu.krasnikov.dto.Pizza;
import ru.nsu.krasnikov.dto.PizzeriaInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Pizzeria class.
 */
public class Pizzeria extends Thread {
    private final Queue<Pizza> orders = new ArrayDeque<>();
    private final List<Thread> chefs = new ArrayList<>();
    private final List<Thread> couriers = new ArrayList<>();
    private final Storage storage;

    /**
     * Pizzeria constructor.
     *
     * @param infoFilePath   path to file with info about storage,
     *                       chefs and couriers.
     * @param ordersFilePath path to file with info about orders.
     */
    public Pizzeria(File infoFilePath, File ordersFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        PizzeriaInfo pizzeriaInfo;
        List<Pizza> pizzaList;

        try {
            pizzeriaInfo = objectMapper.readValue(
                    infoFilePath,
                    PizzeriaInfo.class
            );
            pizzaList = objectMapper.readValue(
                    ordersFilePath,
                    OrdersInfo.class).orders();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        storage = new Storage(pizzeriaInfo.storageSize());

        pizzeriaInfo.chefs().forEach(chef -> chefs.add(
                new Thread(
                        new Chef(this,
                                storage,
                                chef.chefName(),
                                chef.experience()))));

        pizzeriaInfo.couriers().forEach(courier -> couriers.add(
                new Thread(
                        new Courier(this,
                                storage,
                                courier.courierName(),
                                courier.trunkSize(),
                                courier.speed()))));

        orders.addAll(pizzaList);
    }

    /**
     * Take a new order from queue.
     *
     * @return pizza need to be cooked.
     */
    public Pizza getNewOrder() {
        return orders.poll();
    }

    /**
     * Checks if orders queue is empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    /**
     * Thread run function.
     */
    @Override
    public void run() {
        chefs.forEach(Thread::start);
        couriers.forEach(Thread::start);
        try {
            for (Thread ch : chefs) {
                ch.join();
            }
            for (Thread cr : couriers) {
                cr.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get orders queue.
     *
     * @return orders queue.
     */
    public Queue<Pizza> getOrders() {
        return orders;
    }

    /**
     * Get chefs list.
     *
     * @return chefs list.
     */
    public List<Thread> getChefs() {
        return chefs;
    }

    /**
     * Get couriers list.
     *
     * @return couriers list.
     */
    public List<Thread> getCouriers() {
        return couriers;
    }

    /**
     * Get storage.
     *
     * @return storage.
     */
    public Storage getStorage() {
        return storage;
    }
}
