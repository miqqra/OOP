package ru.nsu.krasnikov;

import static ru.nsu.krasnikov.PrimeNumbers.readFromFile;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for checking time and correctness of program.
 */
public class PrimeNumbersTest {
    @Test
    public void smallTest1() throws InterruptedException {
        List<Integer> testList = List.of(new Integer[]{3, 3, 3, 5});

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertFalse(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertFalse(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertFalse(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void smallTest2() throws InterruptedException {
        List<Integer> testList = List.of(new Integer[]{1, 2, 3, 4, 5, 6});

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertTrue(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertTrue(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertTrue(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void smallTest3() throws InterruptedException {
        List<Integer> testList = List.of(new Integer[]{6, 8, 7, 13, 9, 4});

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertTrue(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertTrue(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertTrue(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void test9Numbers() throws InterruptedException {
        List<Integer> testList = readFromFile("test4.txt");

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertFalse(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertFalse(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertFalse(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void test100PrimeNumbers() throws InterruptedException {
        List<Integer> testList = readFromFile("test5.txt");

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertFalse(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertFalse(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertFalse(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void test1000PrimeNumbers() throws InterruptedException {
        List<Integer> testList = readFromFile("test6.txt");

        PrimeNumbers testConsistent = new PrimeNumbersConsistent();
        Assertions.assertFalse(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream();
        Assertions.assertFalse(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread();
        Assertions.assertFalse(testThread.hasPrimeNumber(testList));
    }

    @Test
    public void test1000PrimeNumbersFor4cores() throws InterruptedException {
        List<Integer> testList = readFromFile("test6.txt");

        PrimeNumbers testConsistent = new PrimeNumbersConsistent(4);
        Assertions.assertFalse(testConsistent.hasPrimeNumber(testList));

        PrimeNumbers testParallelStream = new PrimeNumbersParallelStream(4);
        Assertions.assertFalse(testParallelStream.hasPrimeNumber(testList));

        PrimeNumbers testThread = new PrimeNumbersThread(4);
        Assertions.assertFalse(testThread.hasPrimeNumber(testList));
    }
}
