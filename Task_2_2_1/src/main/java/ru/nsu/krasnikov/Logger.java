package ru.nsu.krasnikov;

/**
 * Class for logs.
 */
public class Logger {
    /**
     * Courier delivered pizza.
     *
     * @param courierName name of courier.
     * @param pizzasName  name of pizza.
     */
    public static void courierDeliveredPizza(
            String courierName, String pizzasName) {
        System.out.printf("Courier %s delivered pizza %s\n",
                courierName, pizzasName);
    }

    /**
     * Courier took pizza.
     *
     * @param courierName name of courier.
     * @param pizzasName  name of pizza.
     */
    public static void courierTookPizza(
            String courierName, String pizzasName) {
        System.out.printf(
                "Courier %s took the pizza %s from storage\n",
                courierName, pizzasName);
    }

    /**
     * Chef took new pizza for cooking.
     *
     * @param chefName     name of chef.
     * @param newOrderName name of pizza.
     */
    public static void chefGotNewOrder(
            String chefName, String newOrderName) {
        System.out.printf("Chef %s starts making pizza %s\n",
                chefName, newOrderName);
    }

    /**
     * Chef finished cooking pizza.
     *
     * @param chefName        name of chef.
     * @param readyPizzasName name of cooked pizza.
     */
    public static void chefFinishedOrder(
            String chefName, String readyPizzasName) {
        System.out.printf(
                "Chef %s finished making pizza %s, sent to storage\n",
                chefName, readyPizzasName);
    }

    /**
     * Empty storage message.
     */
    public static void emptyStorage() {
        System.out.println("Storage is empty, waiting...");
    }

    /**
     * Full storage message.
     */
    public static void fullStorage() {
        System.out.println("Storage is full, waiting...");
    }

    /**
     * No orders message.
     */
    public static void noNewOrders() {
        System.out.println("No more new orders, terminating...");
    }
}
