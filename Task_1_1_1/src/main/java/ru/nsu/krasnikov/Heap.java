package ru.nsu.krasnikov;

/**
 * Binary heap class.
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
            siftUp(heap, heapCurrentLength++);
        }
    }

    private void siftUp(int[] array, int idx) {
        if (idx == 0 || (array[idx] >= array[(idx - 1) / 2])) {
            return;
        } else {
            swap(array, idx, (idx - 1) / 2);
            siftUp(array, (idx - 1) / 2);
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void siftDown(int[] array, int elemIndex, int currentLength) {
        int swapIndex;
        if (2 * elemIndex + 1 > currentLength) {
            return;
        } else if (2 * elemIndex + 1 == currentLength) {
            swapIndex = currentLength;
        } else if (array[2 * elemIndex + 1] <= array[2 * elemIndex + 2]) {
            swapIndex = 2 * elemIndex + 1;
        } else {
            swapIndex = 2 * elemIndex + 2;
        }

        if (array[elemIndex] <= array[swapIndex]) {
            return;
        } else {
            swap(array, elemIndex, swapIndex);
            siftDown(array, swapIndex, currentLength - 1);
        }
    }

    private int extractMin(int[] array, int lastIndex) {
        int minElem = array[0];
        if (lastIndex > 0) {
            swap(array, lastIndex, 0);
            siftDown(array, 0, lastIndex - 1);
        }
        return minElem;
    }


    /**
     * Sorts binary heap.
     *
     * @return sorted array with elements we added in initializing function.
     */
    public int[] heapSort() {
        int[] sortedArray = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            sortedArray[i] = extractMin(heap, --heapCurrentLength);
        }
        return sortedArray;
    }
}
