package ru.nsu.krasnikov;

import ru.nsu.krasnikov.dto.Pizza;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Storage class.
 */
public class Storage {
    private final Object FULL_STORAGE = new Object();
    private final Object EMPTY_STORAGE = new Object();
    private final Deque<Pizza> readyPizzas = new ArrayDeque<>();
    private final Integer maxCapacity;
    public boolean runFlag = true;

    /**
     * Storage constructor.
     *
     * @param maxCapacity max capacity of a storage.
     */
    public Storage(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Checks if storage is full.
     *
     * @return true if storage is full, false otherwise.
     */
    public boolean isFull() {
        return readyPizzas.size() >= maxCapacity;
    }

    /**
     * Checks if storage is empty.
     *
     * @return true if storage is empty, false otherwise.
     */
    public boolean isEmpty() {
        return readyPizzas.isEmpty();
    }

    /**
     * Chef thread wait if storage is full.
     *
     * @throws InterruptedException thread sleep error.
     */
    public void waitOnFullStorage() throws InterruptedException {
        synchronized (FULL_STORAGE) {
            FULL_STORAGE.wait();
        }
    }

    /**
     * Notify chef thread if storage no longer full.
     */
    public void notifyOnFullStorage() {
        synchronized (FULL_STORAGE) {
            FULL_STORAGE.notifyAll();
        }
    }

    /**
     * Courier thread wait if storage is empty.
     *
     * @throws InterruptedException thread sleep error.
     */
    public void waitOnEmptyStorage() throws InterruptedException {
        synchronized (EMPTY_STORAGE) {
            EMPTY_STORAGE.wait();
        }
    }

    /**
     * Notify courier thread if storage no longer empty.
     */
    public void notifyOnEmptyStorage() {
        synchronized (EMPTY_STORAGE) {
            EMPTY_STORAGE.notifyAll();
        }
    }

    /**
     * Add pizza to storage.
     *
     * @param pizza cooked pizza.
     */
    public void addPizza(Pizza pizza) {
        synchronized (readyPizzas) {
            readyPizzas.add(pizza);
        }
    }

    /**
     * Give pizzas from a storage.
     *
     * @param trunkSize couriers trunk size.
     * @return list of pizzas which courier can take.
     */
    public List<Pizza> takePizza(int trunkSize) {
        List<Pizza> pizzas = new ArrayList<>();
        int curSize = 0;
        synchronized (readyPizzas) {
            while (true) {
                if (readyPizzas.peek() != null
                        && curSize + readyPizzas.peek().size() <= trunkSize) {
                    pizzas.add(readyPizzas.poll());
                } else {
                    break;
                }
            }
        }
        return pizzas;
    }

    /**
     * Get list of cooked pizzas.
     *
     * @return list of cooked pizzas.
     */
    public Deque<Pizza> getReadyPizzas() {
        return readyPizzas;
    }

    /**
     * Get max capacity of a storage.
     *
     * @return max capacity of a storage.
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }
}
