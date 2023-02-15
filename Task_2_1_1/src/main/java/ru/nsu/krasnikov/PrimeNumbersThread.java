package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding composite integer in list with threads.
 */
public class PrimeNumbersThread extends PrimeNumbers {
    private List<Integer> numbers;

    /**
     * Constructor for PrimeNumbers class. Amount of threads is 8 by default.
     */
    public PrimeNumbersThread() {
        super();
    }

    /**
     * Constructor for PrimeNumbers class.
     *
     * @param coreNumber amount of threads program will use.
     */
    public PrimeNumbersThread(int coreNumber) {
        super(coreNumber);
    }

    /**
     * Find composite number with thread.
     *
     * @param numbers list of integers where need to find a composite number.
     * @return true if this number was found, false - otherwise.
     * @throws InterruptedException in case of incorrect work of thread.
     */
    @Override
    public boolean hasPrimeNumber(List<Integer> numbers) throws InterruptedException {
        this.numbers = numbers;
        List<CustomThread> threads = new ArrayList<>();
        for (int i = 0; i < this.coreNumber; i++) {
            CustomThread thread = new CustomThread(i, coreNumber);
            thread.start();
            threads.add(thread);
        }
        for (CustomThread thread : threads) {
            thread.join();
            if (!thread.isAllNumbersArePrime()) {
                return true;
            }
        }
        return false;
    }

    private class CustomThread extends Thread {
        private final int coreNumber;
        private final int listPart;
        private boolean allNumbersArePrime;

        public CustomThread(int coreNumber, int amountOfCores) {
            this.allNumbersArePrime = true;
            this.coreNumber = coreNumber;
            this.listPart = numbers.size() / amountOfCores + 1;
        }

        public boolean isAllNumbersArePrime() {
            return allNumbersArePrime;
        }

        @Override
        public void run() {
            for (int i = coreNumber * listPart;
                 i <= (coreNumber + 1) * listPart && i < numbers.size();
                 i++
            ) {
                if (!isPrime(numbers.get(i))) {
                    allNumbersArePrime = false;
                    return;
                }
            }
        }
    }
}
