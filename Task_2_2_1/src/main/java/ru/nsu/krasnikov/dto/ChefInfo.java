package ru.nsu.krasnikov.dto;

/**
 * Chef json file.
 *
 * @param chefName   name.
 * @param experience experience, inversely proportional to cooking time.
 */
public record ChefInfo(String chefName, int experience) {
}
