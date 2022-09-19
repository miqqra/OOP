package ru.nsu.krasnikov;

public class MyStack<E> implements StackInterface<E>{
    private int arrayLength;
    private int capacity;
    private E[] stack;

    @SuppressWarnings("unchecked")
    public MyStack() {
        this.capacity = 10;
        this.arrayLength = 0;
        this.stack = (E[])new Object[10];
    }

    @SuppressWarnings("unchecked")
    private void resizeStack()
    {
        E[] newStack = (E[])new Object[capacity*2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
        capacity*=2;
    }

    @Override
    public void push(E element) {
        if (arrayLength == capacity)
        {
            this.resizeStack();
        }
        stack[arrayLength++] = element;
    }

    @Override
    public void pushStack(E[] stack) {
        for (E element : stack) {
            this.push(element);
        }
    }

    @Override
    public E pop() {
        return stack[--arrayLength];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] popStack(int amountOfElements) {
        E[] poppedElements = (E[])(new Object[amountOfElements]);
        for (int i = 0; i < amountOfElements; i++) {
            poppedElements[amountOfElements-i-1] = this.pop();
        }
        return poppedElements;
    }

    @Override
    public int count() {
        return arrayLength;
    }

}
