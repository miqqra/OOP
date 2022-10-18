package ru.nsu.krasnikov;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tree interface with basic tree functions.
 *
 * @param <E> value in node can be anything except primitive types.
 */
public interface TreeInterface<T extends TreeInterface<T, E>, E> extends Iterable<T> {

    /**
     * getParent of chosen node.
     *
     * @param node node, whose parent we want to get.
     * @return parent of chosen node, Null if node is not at the tree or if node is a root.
     */
    T getParent(T node);

    /**
     * get parent of node with chosen value in tree.
     *
     * @param value value of node,whose parent we want to get.
     * @return parent of node, null if node with value is not at the tree or if node is a root.
     */
    T getParent(E value);

    /**
     * get parent for this node.
     *
     * @return parent of this.
     */
    T getParent();

    /**
     * get value of this node.
     *
     * @return value.
     */
    E getValue();

    /**
     * sets value from this node.
     *
     * @param value value, that will be set to the node's value.
     */
    void setValue(E value);

    List<T> getSubtrees(T node);

    List<T> getSubtrees();

    int getSize(T node);

    /**
     * checks if node is root.
     *
     * @return true, if node is root, false otherwise.
     */
    boolean isRoot();

    /**
     * Find node by its value.
     *
     * @param value value of node, whose object we want to get.
     * @return Tree class of node with chosen value, Null if tree doesn't have node with that value.
     */
    T findNode(E value);

    /**
     * Add a new node to the tree.
     *
     * @param node    object parent of subNode. Should be in the tree.
     * @param subNode object that we want to add to the tree
     * @return subNode object in success, null if node is not in tree.
     */
    T add(T node, T subNode)
            throws NoSuchElementException;

    /**
     * Add new node to the tree.
     *
     * @param node  object parent of subNode. Should be in the tree.
     * @param value value of new node we want to add to the tree.
     * @return subNode object in success, null if node is not in tree.
     */
    T add(T node, E value)
            throws NoSuchElementException;

    /**
     * Add new node to the tree. New node will be added as child of this.
     *
     * @param node object that we want to add to the tree.
     * @return node object.
     */
    T add(T node);

    /**
     * Add new node with chosen value to the tree. New node will be added as child of this.
     *
     * @param value value of new node we want to add to the tree.
     * @return subNode object.
     */
    T add(E value);

    /**
     * Removes chosen node from a tree.
     * Children of that node becomes children of nodes parent.
     *
     * @param node node that we want to remove.
     * @return true if node is removed, false if node is not in the tree.
     */
    boolean remove(T node);

    /**
     * Removes node with chosen value from a tree.
     * Children of that node becomes children of nodes parent.
     *
     * @param value value of a node that we want to remove.
     * @return true if node is removed, false if node is not in the tree.
     */
    boolean remove(E value);

    /**
     * Tree iterator.
     * Tree can be iterated in two ways: breadth and depth search (depends on searchMode).
     *
     * @return tree iterator.
     * @throws IllegalStateException if searchMode is null.
     */
    @Override
    Iterator<T> iterator() throws IllegalStateException;

    /**
     * Counts number of changes in a tree.
     *
     * @return number of changes in a tree.
     */
    int getModCount();

    /**
     * print children's values of this node.
     *
     * @return string with node's children's values.
     */
    String printChildren();

    /**
     * to string function for Tree class.
     *
     * @return string with all nodes and its children.
     */
    String toString();
}
