package ru.nsu.krasnikov;

/**
 * Stack interface with basic stack functions.
 *
 * @param <E> stack interface and its subclasses can be used for all non-primitive types.
 */
public interface StackInterface<E> {

    void push(E element);

    void pushStack(E[] stack);

    E pop();

    E[] popStack(int amountOfElements);

    int count();
}
