package ru.nsu.krasnikov;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

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
                if (i.value == value) {
                    return i;
                }
            }
            Tree<E> element = nextNode.findNode(value);
            if (element != null) {
                return element;
            }
        }
        return null;
    }

    @Override
    public Tree<E> add(Tree<E> node, Tree<E> subNode) {
        node.sons.add(subNode);
        subNode.isRoot = false;
        subNode.height = node.height + 1;
        return subNode;
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
        if (this.value == null) {
            this.value = value;
            return this;
        }
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

    public String printChildren() {
        StringBuilder nodeInfo = new StringBuilder(this.value + ": ");
        Iterator<Tree<E>> iter = this.sons.iterator();
        while (iter.hasNext()) {
            nodeInfo.append(iter.next().value).append(" ");
        }
        return nodeInfo.toString();
    }

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
