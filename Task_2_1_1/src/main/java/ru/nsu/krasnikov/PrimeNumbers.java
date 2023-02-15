package ru.nsu.krasnikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Abstract class for finding composite integer in list.
 */
public abstract class PrimeNumbers {
    protected int coreNumber;

    /**
     * Constructor for PrimeNumbers class. Amount of threads is 8 by default.
     */
    public PrimeNumbers() {
        this.coreNumber = 1;
    }

    /**
     * Constructor for PrimeNumbers class.
     *
     * @param coreNumber amount of threads program will use.
     */
    public PrimeNumbers(int coreNumber) {
        this.coreNumber = coreNumber;
    }

    /**
     * Find composite number with threads.
     *
     * @param numbers list of integers where need to find a composite number.
     * @return true if this number was found, false - otherwise.
     */
    public abstract boolean hasPrimeNumber(List<Integer> numbers) throws InterruptedException;

    boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> readFromFile(String fileName) {
        List<Integer> list = new ArrayList<>();
        Scanner in;
        File file = new File(ClassLoader.getSystemResource(fileName).getFile());
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            return list;
        }
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        in.close();
        return list;
    }
}
