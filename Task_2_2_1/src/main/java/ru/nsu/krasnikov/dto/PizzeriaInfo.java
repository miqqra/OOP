package ru.nsu.krasnikov.dto;

import java.util.List;

public record PizzeriaInfo(int storageSize,
                           List<ChefInfo> chefs,
                           List<CourierInfo> couriers) {
}
