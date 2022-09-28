package ru.nsu.krasnikov;

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

    private void breadthFirstSearch() {
    }

    private void depthFirstSearch() {
    }

    private Tree<E> getParent(Tree<E> node){
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
    public Tree<E> remove(Tree<E> node) {

        // todo поиск в arraylist и последующее удаление
        return null;
    }

    @Override
    public E remove(E value) {
        for (int i = 0; i < this.sons.toArray().length; i++) {
            if (sons.get(i).value == value) {
                E removedNodeValue = sons.get(i).value;
                sons.remove(i);
                // todo перенос детей узла на родителя
                return removedNodeValue;
            }
        }
        return null;
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
        return null;
    }
}
