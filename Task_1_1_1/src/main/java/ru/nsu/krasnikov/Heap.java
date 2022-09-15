package ru.nsu.krasnikov;

/**
 * Binary heap class. Uses to sort an array with heap sort.
 * Firstly builds a heap from original array, then there is a function to sort that array.
 *
 */
public class Heap {
    private int[] heap;
    private int heapCurrentLength;
    private final int arrayLength;

    /**
     * Initializes binary heap class with elements from array.
     *
     * @param array array to create binary heap.
     */
    public Heap(int[] array) {
        this.arrayLength = array.length;
        this.heapCurrentLength = 0;
        this.heap = new int[arrayLength];
        buildHeap(array);
    }

    private void buildHeap(int[] array) {
        for (int j : array) {
            heap[heapCurrentLength] = j;
            siftUp(heapCurrentLength++);
        }
    }

    private void siftUp(int idx) {
        if (idx == 0 || (heap[idx] >= heap[(idx - 1) / 2])) {
            return;
        } else {
            swap(idx, (idx - 1) / 2);
            siftUp((idx - 1) / 2);
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void siftDown(int elemIndex, int currentLength) {
        int swapIndex;
        if (2 * elemIndex + 1 > currentLength) {
            return;
        } else if (2 * elemIndex + 1 == currentLength) {
            swapIndex = currentLength;
        } else if (heap[2 * elemIndex + 1] <= heap[2 * elemIndex + 2]) {
            swapIndex = 2 * elemIndex + 1;
        } else {
            swapIndex = 2 * elemIndex + 2;
        }

        if (heap[elemIndex] <= heap[swapIndex]) {
            return;
        } else {
            swap(elemIndex, swapIndex);
            siftDown(swapIndex, currentLength - 1);
        }
    }

    private int extractMin(int lastIndex) {
        int minElem = heap[0];
        if (lastIndex > 0) {
            swap(lastIndex, 0);
            siftDown(0, lastIndex - 1);
        }
        return minElem;
    }


    /**
     * Sorts binary heap.
     *
     * @return sorted array with elements we added in initializing function.
     */
    public int[] heapSort(int[] array) {

        for (int i = 0; i < arrayLength; i++) {
            array[i] = extractMin(--heapCurrentLength);
        }
        return array;
    }
}
