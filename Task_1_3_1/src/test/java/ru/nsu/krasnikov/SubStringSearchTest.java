package ru.nsu.krasnikov;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for searching substring in file.
 */
public class SubStringSearchTest {
    @Test
    public void test1() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test1.txt").getFile()),
                "ab");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{1L, 3L, 6L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test2() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test2.txt").getFile()),
                "abaaba");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{11L, 14L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test3() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test3.txt").getFile()),
                "a");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L, 1L, 2L, 3L, 5L, 6L, 7L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test4() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test4.txt").getFile()),
                "aa");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L, 1L, 2L, 5L, 6L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test5() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test5.txt").getFile()),
                "seven");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{
            28L, 97L, 132L, 140L, 152L, 160L,
            166L, 180L, 192L, 206L, 216L, 230L, 238L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test6() throws IOException {
        SubStringSearch res1 = new SubStringSearch(new File(
                ClassLoader.getSystemResource("test6.txt").getFile()),
                "123456789");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void test7() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                new File(
                        ClassLoader.getSystemResource("test7.txt").getFile()),
                "d");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void noFileTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new SubStringSearch(new File(
                        ClassLoader.getSystemResource("test8.txt").getFile()),
                        "\n"));
    }

    @Test
    void testString1() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "bababaab", "ab");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{1L, 3L, 6L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void testString2() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "cababaccaababaabaabab", "abaaba");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{11L, 14L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void testString3() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "aaaabaaa", "a");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L, 1L, 2L, 3L, 5L, 6L, 7L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void testString4() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "aaaabaaa", "aa");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L, 1L, 2L, 5L, 6L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void testString5() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "123456789", "123456789");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{0L};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }

    @Test
    public void testString6() throws IOException {
        SubStringSearch res1 = new SubStringSearch(
                "cabacaba", "d");
        List<Long> answer = res1.findIndexes();
        Long[] correct = new Long[]{};
        Assertions.assertArrayEquals(answer.toArray(), correct);
    }
}
