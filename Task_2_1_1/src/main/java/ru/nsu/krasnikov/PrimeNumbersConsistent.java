package ru.nsu.krasnikov;

import java.util.List;

/**
 * Class for finding composite integer in list with stream.
 */
public class PrimeNumbersConsistent extends PrimeNumbers {

    /**
     * Constructor for PrimeNumbers class. Amount of threads is 8 by default.
     */
    public PrimeNumbersConsistent() {
        super();
    }

    /**
     * Constructor for PrimeNumbers class.
     *
     * @param coreNumber amount of threads program will use.
     */
    public PrimeNumbersConsistent(int coreNumber) {
        super(coreNumber);
    }

    /**
     * Find composite number with consistent stream.
     *
     * @return true if this number was found, false - otherwise.
     */
    @Override
    public boolean hasPrimeNumber(List<Integer> numbers) {
        return !(numbers.stream().allMatch(this::isPrime));
    }
}
