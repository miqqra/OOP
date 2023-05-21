package ru.nsu.krasnikov.dto;

/**
 * Courier json file.
 *
 * @param courierName name.
 * @param trunkSize   size of couriers trunk.
 * @param speed       speed, inversely proportional to delivery time.
 */
public record CourierInfo(String courierName, int trunkSize, int speed) {
}
