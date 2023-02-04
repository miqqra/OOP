package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching if there is a composite number in array.
 */
public class PrimeNumbers {
    private boolean allNumbersArePrime = true;
    private int coreNumber;
    private int listPart;
    private final List<Integer> numbers;

    /**
     * Constructor for PrimeNumbers class.
     *
     * @param numbers list of integers, where need to find not prime number.
     */
    public PrimeNumbers(List<Integer> numbers) {
        this.numbers = numbers;
        this.coreNumber = 8;
        this.listPart = numbers.size() / coreNumber + 1;
    }

    /**
     * Find composite number with consistent stream.
     *
     * @return true if this number was found, false - otherwise.
     */
    public boolean hasPrimeNumberConsistent() {
        return numbers.stream().anyMatch(number -> !isPrime(number));
    }

    /**
     * Find composite number with parallel stream.
     *
     * @return true if this number was found, false - otherwise.
     */
    public boolean hasPrimeNumberParallelStream() {
        return numbers.stream().parallel().anyMatch(number -> !isPrime(number));
    }

    /**
     * Find composite number with threads.
     *
     * @return true if this number was found, false - otherwise.
     * @throws InterruptedException thread incorrect work exception.
     */
    public boolean hasPrimeNumberThread() throws InterruptedException {
        allNumbersArePrime = true;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < this.coreNumber; i++) {
            Thread thread = new CustomThread(i);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return !allNumbersArePrime;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Change number of threads, that will solve the problem.
     *
     * @param coreNumber new amount of threads.
     */
    public void setCoreNumber(int coreNumber) {
        this.coreNumber = coreNumber;
        this.listPart = numbers.size() / coreNumber + 1;
    }

    private class CustomThread extends Thread {
        private final int coreNumber;

        public CustomThread(int coreNumber) {
            this.coreNumber = coreNumber;
        }

        @Override
        public void run() {
            for (int i = coreNumber * listPart; i <= (coreNumber + 1) * listPart && i < numbers.size(); i++) {
                if (!isPrime(numbers.get(i))) {
                    allNumbersArePrime = false;
                    return;
                }
            }
        }
    }
}
