package ru.nsu.krasnikov.dto;

/**
 * Pizza json file.
 *
 * @param name         pizzas name.
 * @param size         pizzas size.
 * @param deliveryTime delivery time.
 */
public record Pizza(String name, int size, int deliveryTime) {
}
