package ru.nsu.krasnikov;

/**
 * HeapSort class. Uses to sort an array with heap sort.
 * Firstly builds a binary heap from original array, then there is a function to sort that array.
 */
public class HeapSort {
    private int heapCurrentLength;
    private final int arrayLength;

    /**
     * Initializes binary heap class with elements from array.
     *
     * @param array array to create binary heap.
     */
    public HeapSort(int[] array) {
        this.arrayLength = array.length;
        this.heapCurrentLength = 0;
        buildHeap(array);
    }

    private void buildHeap(int[] array) {
        for (int j = 0; j < arrayLength; j++) {
            siftUp(array, heapCurrentLength++);
        }
    }

    private void siftUp(int[] heap, int idx) {
        if (idx == 0 || (heap[idx] <= heap[(idx - 1) / 2])) {
            return;
        } else {
            swap(heap, idx, (idx - 1) / 2);
            siftUp(heap, (idx - 1) / 2);
        }
    }

    private void swap(int[] heap, int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void siftDown(int[] heap, int elemIndex, int currentLength) {
        int swapIndex;
        if (2 * elemIndex + 1 > currentLength) {
            return;
        } else if (2 * elemIndex + 1 == currentLength) {
            swapIndex = currentLength;
        } else if (heap[2 * elemIndex + 1] >= heap[2 * elemIndex + 2]) {
            swapIndex = 2 * elemIndex + 1;
        } else {
            swapIndex = 2 * elemIndex + 2;
        }

        if (heap[elemIndex] >= heap[swapIndex]) {
            return;
        } else {
            swap(heap, elemIndex, swapIndex);
            siftDown(heap, swapIndex, currentLength - 1);
        }
    }

    private void extractMax(int[] heap, int lastIndex) {
        if (lastIndex > 0) {
            swap(heap, lastIndex, 0);
            siftDown(heap, 0, lastIndex - 1);
        }
    }


    /**
     * Sorts binary heap.
     *
     * @return sorted array with elements we added in initializing function.
     */
    public int[] heapSort(int[] array) {

        for (int i = 0; i < arrayLength; i++) {
            extractMax(array, --heapCurrentLength);
        }
        return array;
    }
}