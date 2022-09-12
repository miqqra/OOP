package ru.nsu.krasnikov;

public class HeapTest {
    public static void heapTest(String[] args) {
        int[] array = {1, 1, 1, 1, 2, 3, 3, 3, 3, 1};

        Heap heapArray = new Heap(array);
        int[] sortedArray = heapArray.heapSort();

        for (int i = 0; i < sortedArray.length; i++)
            System.out.println(sortedArray[i] + " ");
    }
}
