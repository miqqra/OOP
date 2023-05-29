package ru.nsu.krasnikov.dto;

import java.util.List;

/**
 * Orders json file.
 *
 * @param orders list of orders.
 */
public record OrdersInfo(List<Pizza> orders) {
}
