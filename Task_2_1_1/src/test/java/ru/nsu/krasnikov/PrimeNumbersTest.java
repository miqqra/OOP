package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Tests for checking time and correctness of program.
 */
public class PrimeNumbersTest {
    @Test
    public void test1() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(List.of(new Integer[]{3, 3, 3, 5}));
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test1:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test2() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(List.of(new Integer[]{1, 2, 3, 4, 5, 6}));
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test2:\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test3() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(List.of(new Integer[]{6, 8, 7, 13, 9, 4}));
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test3:\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertTrue(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test4() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test4.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test4:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test5() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test5.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test5:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test6() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test6.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test6:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test7() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test7.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test7:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test8() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test8.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test8:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test8For4cores() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test8.txt")
        );
        long timeBefore, timeAfter;

        test.setCoreNumber(4);

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test8 for 4 cores:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    @Test
    public void test9() throws InterruptedException, IOException {
        PrimeNumbers test = new PrimeNumbers(
                readFromFile("test9.txt")
        );
        long timeBefore, timeAfter;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        StringBuilder str = new StringBuilder();
        str.append("Test9:\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberConsistent());
        timeAfter = new Date().getTime();
        str.append("\tConsistent test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberParallelStream());
        timeAfter = new Date().getTime();
        str.append("\tParallel stream test: ").append(timeAfter - timeBefore).append("\n");

        timeBefore = new Date().getTime();
        Assertions.assertFalse(test.hasPrimeNumberThread());
        timeAfter = new Date().getTime();
        str.append("\tThread test: ").append(timeAfter - timeBefore).append("\n");
        writer.write(str.toString());
        writer.close();
    }

    private List<Integer> readFromFile(String fileName) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        Scanner in = new Scanner(new File(ClassLoader.getSystemResource(fileName).getFile()));
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        return list;
    }
}
