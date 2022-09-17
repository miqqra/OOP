package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Arrays;

public class MyStack<E> {
    private int arrayLength;
    private int capacity;
    private Object[] stack;

    public MyStack() {
        this.capacity = 10;
        this.arrayLength = 0;
        this.stack = new Object[10];
    }

    private void resizeStack()
    {
        Object[] newStack = new Object[capacity*2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
        capacity*=2;
    }

    public void push(E element) {
        if (arrayLength == capacity)
        {
            resizeStack();
        }
        stack[arrayLength++] = element;
    }

    public void pushStack(E[] stack) {
        for (E element : stack) {
            this.push(element);
        }
    }

    public void pop() {
        arrayLength--;
    }

    public void popStack(int amountOfElements) {
        for (int i = 0; i < amountOfElements; i++) {
            this.pop();
        }
    }

    public int count() {
        return arrayLength;
    }

}
