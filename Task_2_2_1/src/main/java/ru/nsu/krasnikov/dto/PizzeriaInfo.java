package ru.nsu.krasnikov.dto;

import java.util.List;

/**
 * Pizzeria json file.
 *
 * @param storageSize storage size.
 * @param chefs       chefs working in pizzeria.
 * @param couriers    couriers working in pizzeria.
 */
public record PizzeriaInfo(int storageSize,
                           List<ChefInfo> chefs,
                           List<CourierInfo> couriers) {
}
