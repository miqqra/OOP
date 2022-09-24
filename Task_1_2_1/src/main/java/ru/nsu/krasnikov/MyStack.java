package ru.nsu.krasnikov;

/**
 * Stack data structure generic class.
 * Works like "last in, first out".
 *
 * @param <E> Generic. Class can be used for all non-primitive types.
 */
public class MyStack<E> implements StackInterface<E> {
    private int arrayLength;
    private int capacity;
    private E[] stack;

    /**
     * Function for initializing stack.
     * Creates array of Objects with cast to element's type.
     * Stores capacity of that array and number of elements there.
     */
    public MyStack(E[] stack) {
        this.capacity = 10;
        this.arrayLength = 0;
        this.stack = stack;
    }

    @SuppressWarnings("unchecked")
    public MyStack() {
        this.capacity = 10;
        this.arrayLength = 0;
        this.stack = (E[]) new Object[10];
    }

    @SuppressWarnings("unchecked")
    private void decreaseArraySize()
    {
        E[] newStack = (E[]) new Object[capacity/2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
        capacity /= 2;
    }

    @SuppressWarnings("unchecked")
    private void increaseArraySize() {
        E[] newStack = (E[]) new Object[capacity * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
        capacity *= 2;
    }

    /**
     * pushes element into stack.
     * If size of array won't be enough, we increase the size of array.
     *
     * @param element element that will be pushed into stack.
     */
    @Override
    public void push(E element) {
        if (arrayLength == capacity) {
            this.increaseArraySize();
        }
        stack[arrayLength++] = element;
    }

    /**
     * Pushes array of elements into stack.
     * Uses function push.
     *
     * @param stack array of elements which will be pushed into stack.
     */
    @Override
    public void pushStack(MyStack<E> stack) {
        for (E element : stack.stack) {
            this.push(element);
        }
    }


    /**
     * removes last element from the stack.
     *
     * @return element, that was removed.
     */
    @Override
    public E pop() {
        E res = stack[--arrayLength];
        if (arrayLength == capacity/2)
        {
            this.decreaseArraySize();
        }
        return res;
    }

    /**
     * Removes certain number of elements from the stack.
     *
     * @param amountOfElements number of elements, which will be removed from the stack.
     * @return array of elements, which were removed from the stack.
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] popStack(int amountOfElements) {
        E[] poppedElements = (E[]) (new Object[amountOfElements]);
        for (int i = 0; i < amountOfElements; i++) {
            poppedElements[amountOfElements - i - 1] = this.pop();
        }
        return poppedElements;
    }

    /**
     * counts number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int count() {
        return arrayLength;
    }

}
