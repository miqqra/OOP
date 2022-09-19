package ru.nsu.krasnikov;

public interface StackInterface<E> {

    void push(E element);

    void pushStack(E[] stack);

    E pop();

    E[] popStack(int amountOfElements);

    int count();
}
