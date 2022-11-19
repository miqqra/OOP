package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordBookTest {
    @Test
    public void test1() {
        RecordBook book = new RecordBook();
        book.addSubject("Введение в алгебру и анализ", 1, 4);
        book.addSubject("Введение в дискретную математику и математическую логику", 1, 5);
        book.addSubject("Декларативное программирование", 1, 5);
        book.addSubject("Императивное программирование", 1, 5);
        book.addSubject("История", 1, 5);
        book.addSubject("Основы культуры речи", 1, 5);

        book.addSubject("Введение в алгебру и анализ", 2, 5);
        book.addSubject("Введение в дискретную математику и математическую логику", 2, 5);
        book.addSubject("Декларативное программирование", 2, 5);
        book.addSubject("Императивное программирование", 2, 5);
        book.addSubject("Иностранный язык", 2, 4);
        book.addSubject("Цифровые платформы", 2, 4);

        Assertions.assertEquals(book.getAverageScore(), 4.75);
        Assertions.assertTrue(book.canGetIncreasedScholarship(1));
        Assertions.assertTrue(book.canGetRedDiploma());
    }

    @Test
    public void test2() {
        RecordBook book = new RecordBook();

        Assertions.assertFalse(book.canGetIncreasedScholarship(1));
        Assertions.assertEquals(book.getAverageScore(1), 0);
        Assertions.assertEquals(book.getAverageScore(), 0);

        book.setQualificationWorkScore(5);

        Assertions.assertFalse(book.canGetRedDiploma());

        book.addSubject("Введение в алгебру и анализ", 1, 5);
        book.addSubject("Введение в дискретную математику и математическую логику", 1, 5);
        book.addSubject("Декларативное программирование", 1, 5);
        book.addSubject("Императивное программирование", 1, 5);
        book.addSubject("История", 1, 5);
        book.addSubject("Основы культуры речи", 1, 5);

        Assertions.assertTrue(book.canGetRedDiploma());

        book.addSubject("Введение в алгебру и анализ", 2, 5);
        book.addSubject("Введение в дискретную математику и математическую логику", 2, 5);
        book.addSubject("Декларативное программирование", 2, 3);
        book.addSubject("Императивное программирование", 2, 5);
        book.addSubject("Иностранный язык", 2, 4);
        book.addSubject("Цифровые платформы", 2, 4);

        Assertions.assertEquals(book.getAverageScore(1), 5);
        Assertions.assertFalse(book.canGetRedDiploma());
        Assertions.assertTrue(book.canGetIncreasedScholarship(1));
        Assertions.assertFalse(book.canGetIncreasedScholarship(2));
    }
}
