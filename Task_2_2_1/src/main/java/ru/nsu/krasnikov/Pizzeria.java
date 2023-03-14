package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.Queue;

public class Pizzeria {
    private int bakersNumber;
    private int couriersNumber;
    private int storageCapacity;
    private Queue<Order> orders;

    public Pizzeria(int bakersNumber,
                    int couriersNumber,
                    int storageCapacity) {
        this.bakersNumber = bakersNumber;
        this.couriersNumber = couriersNumber;
        this.storageCapacity = storageCapacity;
        this.orders = new ArrayDeque<>();
    }
}
