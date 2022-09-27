package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HeapSortTest class for testing correct work of heapsort function.
 */
public class HeapSortTest {

    @Test
    public void heapTestEmptyArray() {
        int[] array = {};

        HeapSort heapArray = new HeapSort(array);
        int[] sortedArray = heapArray.heapSort(array);
        int[] correctArray = {};

        Assertions.assertArrayEquals(correctArray, sortedArray);
    }

    @Test
    public void heapTestOneElemArray() {
        int[] array = {1};

        HeapSort heapArray = new HeapSort(array);
        int[] sortedArray = heapArray.heapSort(array);
        int[] correctArray = {1};

        Assertions.assertArrayEquals(correctArray, sortedArray);
    }

    @Test
    public void heapTestTwoElemsArray() {
        int[] array = {2, 1};

        HeapSort heapArray = new HeapSort(array);
        int[] sortedArray = heapArray.heapSort(array);
        int[] correctArray = {1, 2};

        Assertions.assertArrayEquals(correctArray, sortedArray);
    }

    @Test
    public void heapTestFourElemsArray() {
        int[] array = {2, 4, 3, 1};

        HeapSort heapArray = new HeapSort(array);
        int[] sortedArray = heapArray.heapSort(array);
        int[] correctArray = {1, 2, 3, 4};

        Assertions.assertArrayEquals(correctArray, sortedArray);
    }

    @Test
    public void heapTestMoreElemsArray() {
        int[] array = {2, 4, 3, 1, 6, 6, 8, 9, 0, -10};

        HeapSort heapArray = new HeapSort(array);
        int[] sortedArray = heapArray.heapSort(array);
        int[] correctArray = {-10, 0, 1, 2, 3, 4, 6, 6, 8, 9};

        Assertions.assertArrayEquals(correctArray, sortedArray);
    }
}
