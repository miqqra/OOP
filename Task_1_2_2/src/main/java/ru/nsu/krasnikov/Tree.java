package ru.nsu.krasnikov;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Tree<E> implements TreeInterface<E> {

    private boolean isRoot;
    private int height;
    private E value;
    private List<Tree<E>> sons;

    private Tree(E value, boolean isRoot, int height) {
        this.height = height;
        this.value = value;
        this.isRoot = isRoot;
        this.sons = new ArrayList<>();
    }

    public Tree(E value) {
        this.height = 0;
        this.value = value;
        this.isRoot = true;
        this.sons = new ArrayList<>();
    }

    public Tree() {
        this.height = 0;
        this.value = null;
        this.isRoot = true;
        this.sons = new ArrayList<>();
    }

    public Tree<E> getParent(Tree<E> node) {
        if (this.sons.contains(node)) {
            return this;
        }
        Iterator<Tree<E>> iter = this.sons.iterator();
        while (iter.hasNext()) {
            Tree<E> nextNode = iter.next();
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
        //throws NoSuchElementException || return null
    }

    public Tree<E> getParent(E value) {
        if (this.value == value) {
            return this;
        }
        Iterator<Tree<E>> iter = this.sons.iterator();
        while (iter.hasNext()) {
            Tree<E> nextNode = iter.next();
            for (Tree<E> i : nextNode.sons) {
                if (i.value == value)
                    return nextNode;
            }
            Tree<E> parent = nextNode.getParent(value);
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }

    @Override
    public Tree<E> findNode(E value) {
        if (this.value == value) {
            return this;
        }
        Iterator<Tree<E>> iter = this.sons.iterator();
        while (iter.hasNext()) {
            Tree<E> nextNode = iter.next();
            for (Tree<E> i : nextNode.sons) {
                if (i.value == value)
                    return i;
            }
            Tree<E> element = nextNode.findNode(value);
            if (element != null) {
                return element;
            }
        }
        return null;
    }

    @Override
    public Tree<E> add(Tree<E> node, E value) {
        Tree<E> newNode = new Tree<>(value, false, node.height + 1);
        node.sons.add(newNode);
        return newNode;
    }

    @Override
    public Tree<E> add(Tree<E> node) {
        if (this.value == null) {
            this.height = 0;
            this.value = node.value;
            this.sons = node.sons;
            return this;
        }
        node.isRoot = false;
        node.height = this.height + 1;
        this.sons.add(node);
        return node;
    }

    @Override
    public Tree<E> add(E value) {
        Tree<E> newNode = new Tree<>(value, false, this.height + 1);
        this.sons.add(newNode);
        return newNode;
    }

    @Override
    public boolean remove(Tree<E> node) {
        if (this == node && this.isRoot) {
            this.value = null;
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
        //throws NoSuchElementException
    }

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
            }
        }
        //todo changing heights
        return true;
    }

    @Override
    public int getHeight(Tree<E> node) {
        return node.height;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public E getValue() {
        return this.value;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public boolean isRoot() {
        return this.isRoot;
    }

    @Override
    public String toString() {
        StringBuilder nodeInfo = new StringBuilder(this.value + ": " + this.sons + "\n");
        Iterator<Tree<E>> iter = this.sons.iterator();
        while (iter.hasNext()) {
            nodeInfo.append(iter.next().toString());
        }
        return nodeInfo.toString();
    }
}
