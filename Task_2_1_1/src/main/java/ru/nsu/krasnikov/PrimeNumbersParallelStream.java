package ru.nsu.krasnikov;

import java.util.List;

/**
 * Class for finding composite integer in list with parallel stream.
 */
public class PrimeNumbersParallelStream extends PrimeNumbers {

    /**
     * Constructor for PrimeNumbers class. Amount of threads is 8 by default.
     */
    public PrimeNumbersParallelStream() {
        super();
    }

    /**
     * Constructor for PrimeNumbers class.
     *
     * @param coreNumber amount of threads program will use.
     */
    public PrimeNumbersParallelStream(int coreNumber) {
        super(coreNumber);
    }

    /**
     * Find composite number with parallel stream.
     *
     * @return true if this number was found, false - otherwise.
     */
    @Override
    public boolean hasPrimeNumber(List<Integer> numbers) {
        return !(numbers.stream().parallel().allMatch(this::isPrime));
    }
}
