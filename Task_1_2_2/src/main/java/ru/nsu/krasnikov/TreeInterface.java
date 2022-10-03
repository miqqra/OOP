package ru.nsu.krasnikov;

import java.util.NoSuchElementException;

/**
 * Tree interface with basic tree functions.
 *
 * @param <E> value in node can be anything except primitive types.
 */
public interface TreeInterface<E> {

    Tree<E> add(Tree<E> node, Tree<E> subNode)
            throws NoSuchElementException, ExistingElementException;

    Tree<E> add(Tree<E> node, E value)
            throws NoSuchElementException, ExistingElementException;

    Tree<E> add(Tree<E> node) throws ExistingElementException;

    Tree<E> add(E value) throws ExistingElementException;

    Tree<E> findNode(E value) throws NoSuchElementException;

    Tree<E> getParent(Tree<E> node);

    Tree<E> getParent(E value);

    boolean remove(Tree<E> node);

    boolean remove(E value);

    E getValue();

    void setValue(E value);

    boolean isRoot();

    String toString();
}
