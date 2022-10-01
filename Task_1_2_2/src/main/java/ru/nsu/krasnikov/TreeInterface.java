package ru.nsu.krasnikov;

/**
 * Tree interface with basic tree functions.
 *
 * @param <E> value in node can be anything except primitive types.
 */
public interface TreeInterface<E> {

    Tree<E> add(Tree<E> node, Tree<E> subNode);

    Tree<E> add(Tree<E> node, E value);

    Tree<E> add(Tree<E> node);

    Tree<E> add(E value);

    Tree<E> findNode(E value);

    Tree<E> getParent(Tree<E> node);

    Tree<E> getParent(E value);

    boolean remove(Tree<E> node);

    boolean remove(E value);

    int getHeight(Tree<E> root);

    int getHeight();

    E getValue();

    void setValue(E value);

    boolean isRoot();

    String toString();
}
