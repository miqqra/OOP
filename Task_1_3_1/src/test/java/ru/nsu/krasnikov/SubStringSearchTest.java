package ru.nsu.krasnikov;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubStringSearchTest {
    @Test
    public void test1() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test1.txt").getFile(), "ab");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{1, 3, 6};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test2() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test2.txt").getFile(), "abaaba");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{11, 14};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test3() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test3.txt").getFile(), "a");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{0, 1, 2, 3, 5, 6, 7};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test4() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test4.txt").getFile(), "aa");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{0, 1, 2, 5, 6};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test5() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test5.txt").getFile(), "seven");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{34, 113, 152, 161, 174, 182, 189, 203, 216, 230, 241, 255, 263};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test6() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test6.txt").getFile(), "123456789");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{0};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test7() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test7.txt").getFile(), "d");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test8() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                ClassLoader.getSystemResource("test8.txt").getFile(), "\n");
        List<Integer> answer = res1.findIndexes();
        Integer[] correct = new Integer[]{1, 3, 5, 7, 9, 11, 13, 15};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void noFileTest() {
        Assertions.assertThrows(FileNotFoundException.class,
                () -> new SubStringSearch("test9.txt", "\n"));
    }
}
