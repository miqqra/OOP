package ru.nsu.krasnikov;

import ru.nsu.krasnikov.dto.Pizza;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Storage {
    private final Object FULL_STORAGE = new Object();
    private final Object EMPTY_STORAGE = new Object();
    private final Deque<Pizza> readyPizzas = new ArrayDeque<>();
    private final Integer maxCapacity;
    public boolean runFlag = true;

    public Storage(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isFull() {
        return readyPizzas.size() >= maxCapacity;
    }

    public boolean isEmpty() {
        return readyPizzas.isEmpty();
    }

    public void waitOnFullStorage() throws InterruptedException {
        synchronized (FULL_STORAGE) {
            FULL_STORAGE.wait();
        }
    }

    public void notifyOnFullStorage() {
        synchronized (FULL_STORAGE) {
            FULL_STORAGE.notifyAll();
        }
    }

    public void waitOnEmptyStorage() throws InterruptedException {
        synchronized (EMPTY_STORAGE) {
            EMPTY_STORAGE.wait();
        }
    }

    public void notifyOnEmptyStorage() {
        synchronized (EMPTY_STORAGE) {
            EMPTY_STORAGE.notifyAll();
        }
    }

    public void addPizza(Pizza pizza) {
        synchronized (readyPizzas) {
            readyPizzas.add(pizza);
        }
    }

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

    public Deque<Pizza> getReadyPizzas() {
        return readyPizzas;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }
}
