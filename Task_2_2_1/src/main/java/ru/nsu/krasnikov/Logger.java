package ru.nsu.krasnikov;

public class Logger {
    public static void courierDeliveredPizza(
            String courierName, String pizzasName) {
        System.out.printf("Courier %s delivered pizza %s\n",
                courierName, pizzasName);
    }

    public static void courierTookPizza(
            String courierName, String pizzasName) {
        System.out.printf(
                "Courier %s took the pizza %s from storage\n",
                courierName, pizzasName);
    }

    public static void chefGotNewOrder(
            String chefName, String newOrderName) {
        System.out.printf("Chef %s starts making pizza %s\n",
                chefName, newOrderName);
    }

    public static void chefFinishedOrder(
            String chefName, String readyPizzasName) {
        System.out.printf(
                "Chef %s finished making pizza %s, sent to storage\n",
                chefName, readyPizzasName);
    }

    public static void emptyStorage() {
        System.out.println("Storage is empty, waiting...");
    }

    public static void fullStorage() {
        System.out.println("Storage is full, waiting...");
    }

    public static void noNewOrders() {
        System.out.println("No more new orders, terminating...");
    }
}
