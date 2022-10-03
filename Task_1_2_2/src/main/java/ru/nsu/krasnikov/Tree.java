package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tree class.
 * One node can have any number of children.
 *
 * @param <E> value in node can be anything except primitive types.
 */
public class Tree<E> implements TreeInterface<E> {

    private boolean isRoot;
    private E value;
    private List<Tree<E>> sons;

    private Tree(E value, boolean isRoot) {
        this.value = value;
        this.isRoot = isRoot;
        this.sons = new ArrayList<>();
    }

    /**
     * Constructor for initializing new node in tree.
     *
     * @param value New node's value.
     */
    public Tree(E value) {
        this.value = value;
        this.isRoot = true;
        this.sons = new ArrayList<>();
    }

    /**
     * Constructor for initializing new node in tree.
     * Value is null by default.
     */
    public Tree() {
        this.value = null;
        this.isRoot = true;
        this.sons = new ArrayList<>();
    }

    /**
     * getParent of chosen node.
     *
     * @param node node, whose parent we want to get.
     * @return parent of chosen node, Null if node is not at the tree or if node is a root.
     */
    public Tree<E> getParent(Tree<E> node) {
        if (this.sons.contains(node)) {
            return this;
        }
        for (Tree<E> nextNode : this.sons) {
            if (nextNode.sons.contains(node)) {
                return nextNode;
            } else {
                Tree<E> parent = nextNode.getParent(node);
                if (parent != null) {
                    return parent;
                }
            }
        }
        return null;
    }

    /**
     * get parent of node with chosen value in tree.
     *
     * @param value value of node,whose parent we want to get.
     * @return parent of node, null if node with value is not at the tree or if node is a root.
     */
    public Tree<E> getParent(E value) {
        for (Tree<E> nextNode : this.sons) {
            if (nextNode.value == value) {
                return this;
            }
            for (Tree<E> i : nextNode.sons) {
                if (i.value == value) {
                    return nextNode;
                }
            }
            Tree<E> parent = nextNode.getParent(value);
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }

    /**
     * Find node by its value.
     *
     * @param value value of node, whose object we want to get.
     * @return Tree class of node with chosen value, Null if tree doesn't have node with that value.
     */
    @Override
    public Tree<E> findNode(E value) throws NoSuchElementException {
        if (this.value == value) {
            return this;
        }
        for (Tree<E> nextNode : this.sons) {
            for (Tree<E> i : nextNode.sons) {
                if (i.value == value) {
                    return i;
                }
            }
            Tree<E> element;
            try{
                element = nextNode.findNode(value);
            }
            catch (NoSuchElementException e){
                continue;
            }
            return element;
        }
        throw new NoSuchElementException();
    }

    /**
     * Add a new node to the tree.
     *
     * @param node    object parent of subNode. Should be in the tree.
     * @param subNode object that we want to add to the tree
     * @return subNode object in success, null if node is not in tree.
     */
    @Override
    public Tree<E> add(Tree<E> node, Tree<E> subNode) throws NoSuchElementException, ExistingElementException {
        try {
            this.findNode(node.value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }

        try {
            this.findNode(subNode.value);
        } catch (NoSuchElementException e) {
            node.sons.add(subNode);
            subNode.isRoot = false;
            return subNode;
        }
        throw new ExistingElementException();
    }

    /**
     * Add new node to the tree.
     *
     * @param node  object parent of subNode. Should be in the tree.
     * @param value value of new node we want to add to the tree.
     * @return subNode object in success, null if node is not in tree.
     */
    @Override
    public Tree<E> add(Tree<E> node, E value) throws NoSuchElementException, ExistingElementException {
        try {
            this.findNode(node.value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }

        try {
            this.findNode(value);
        } catch (NoSuchElementException e) {
            Tree<E> newNode = new Tree<>(value, false);
            node.sons.add(newNode);
            return newNode;
        }
        throw new ExistingElementException();
    }

    /**
     * Add new node to the tree. New node will be added as child of this.
     *
     * @param node object that we want to add to the tree.
     * @return node object.
     */
    @Override
    public Tree<E> add(Tree<E> node) throws ExistingElementException{
        try {
            this.findNode(node.value);
        } catch (NoSuchElementException e) {
            if (this.value == null) {
                this.value = node.value;
                this.sons = node.sons;
                return this;
            }
            node.isRoot = false;
            this.sons.add(node);
            return node;
        }
        throw new ExistingElementException();
    }

    /**
     * Add new node with chosen value to the tree. New node will be added as child of this.
     *
     * @param value value of new node we want to add to the tree.
     * @return subNode object.
     */
    @Override
    public Tree<E> add(E value) throws ExistingElementException{
        try {
            this.findNode(value);
        } catch (NoSuchElementException e) {
            if (this.value == null) {
                this.value = value;
                return this;
            }
            Tree<E> newNode = new Tree<>(value, false);
            this.sons.add(newNode);
            return newNode;
        }
        throw new ExistingElementException();
    }

    /**
     * Removes chosen node from a tree.
     * Children of that node becomes children of nodes parent.
     *
     * @param node node that we want to remove.
     * @return true if node is removed, false if node is not in the tree.
     */
    @Override
    public boolean remove(Tree<E> node) {
        if (this == node && this.isRoot) {
            node.value = null;
            return true;
        }
        Tree<E> parent = this.getParent(node);
        if (parent != null) {
            parent.sons.addAll(node.sons);
            node.sons.removeAll(node.sons);
            parent.sons.remove(node);
            return true;
        }
        return false;
    }

    /**
     * Removes node with chosen value from a tree.
     * Children of that node becomes children of nodes parent.
     *
     * @param value value of a node that we want to remove.
     * @return true if node is removed, false if node is not in the tree.
     */
    @Override
    public boolean remove(E value) {
        if (this.value == value && this.isRoot) {
            this.value = null;
            return true;
        }
        Tree<E> parent = this.getParent(value);
        if (parent == null) {
            return false;
        }
        for (Tree<E> node : parent.sons) {
            if (node.value == value) {
                parent.sons.addAll(node.sons);
                node.sons.removeAll(node.sons);
                parent.sons.remove(node);
                return true;
            }
        }
        return false;
    }

    /**
     * get value of this node.
     *
     * @return value.
     */
    @Override
    public E getValue() {
        return this.value;
    }

    /**
     * sets value from this node.
     *
     * @param value value, that will be set to the node's value.
     */
    @Override
    public void setValue(E value) {
        this.value = value;
    }

    /**
     * checks if node is root.
     *
     * @return true, if node is root, false otherwise.
     */
    @Override
    public boolean isRoot() {
        return this.isRoot;
    }

    /**
     * print children's values of this node.
     *
     * @return string with node's children's values.
     */
    public String printChildren() {
        StringBuilder nodeInfo = new StringBuilder(this.value + ": ");
        for (Tree<E> son : this.sons) {
            nodeInfo.append(son.value).append(" ");
        }
        return nodeInfo.toString();
    }

    /**
     * to string function for Tree class.
     *
     * @return string with all nodes and its children.
     */
    @Override
    public String toString() {
        StringBuilder treeInfo = new StringBuilder();
        ArrayDeque<Tree<E>> nodesQueue = new ArrayDeque<>();
        nodesQueue.add(this);
        while (!(nodesQueue.isEmpty())) {
            Tree<E> node = nodesQueue.pop();
            nodesQueue.addAll(node.sons);
            treeInfo.append(node.printChildren()).append("\n");
        }
        return treeInfo.toString();
    }
}