package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Tree class.
 * One node can have any number of children.
 *
 * @param <E> value in node can be anything except primitive types.
 */
public class Tree<E> implements TreeInterface<Tree<E>, E> {

    /**
     * Class for custom tree iterator. Tree can be iterated two ways, breadth and depth search.
     *
     * @param <T> extends Tree of any type of values.
     */
    public static class TreeIterator<T extends Tree<?>> implements Iterator<T> {
        private final Stack<T> stack;
        private final Queue<T> queue;
        private final IteratorType mode;
        private final T root;
        private final int expectedModCount;

        public TreeIterator(IteratorType mode, T startNode) throws IllegalStateException {
            this.mode = mode;
            root = startNode;
            stack = new Stack<>();
            queue = new ArrayDeque<>();
            expectedModCount = startNode.getModCount();

            switch (mode) {
                case DFS: {
                    stack.push(startNode);
                    break;
                }
                case BFS: {
                    queue.add(startNode);
                    break;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }

        @Override
        public boolean hasNext() throws IllegalStateException {
            switch (mode) {
                case BFS: {
                    return !(queue.isEmpty());
                }
                case DFS: {
                    return !(stack.isEmpty());
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() throws NoSuchElementException, ConcurrentModificationException {
            int curModCount = root.getModCount();
            if (curModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            switch (mode) {
                case BFS: {
                    T node = queue.remove();
                    queue.addAll((Collection<? extends T>) node.getSubtrees());
                    return node;
                }
                case DFS: {
                    T node = stack.pop();
                    stack.addAll((Collection<? extends T>) node.getSubtrees());
                    return node;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
    }

    public enum IteratorType {
        BFS, DFS
    }

    private E value;
    private final List<Tree<E>> sons;
    private Tree<E> parent;
    private Tree<E> root;
    private IteratorType mode;
    private int modCount;

    /**
     * Constructor for initializing new node in tree.
     *
     * @param value New node's value.
     */
    public Tree(E value) {
        this.value = value;
        this.sons = new ArrayList<>();
        this.parent = null;
        this.root = this;
    }

    /**
     * Constructor for initializing new node in tree.
     * Value is null by default.
     */
    public Tree() {
        this.value = null;
        this.parent = null;
        this.root = this;
        this.sons = new ArrayList<>();
    }

    @Override
    public Tree<E> getParent(Tree<E> node) {
        return node.parent;
    }

    @Override
    public Tree<E> getParent(E value) {
        return findNode(value).parent;
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
    public List<Tree<E>> getSubtrees(Tree<E> node) {
        return node.sons;
    }

    @Override
    public List<Tree<E>> getSubtrees() {
        return this.sons;
    }

    @Override
    public int getSize(Tree<E> node) {
        return node.sons.size();
    }

    @Override
    public boolean isRoot() {
        return this.equals(root);
    }

    @Override
    public Tree<E> findNode(E value) {
        for (Tree<E> node : root) {
            if (node.getValue() == value) {
                return node;
            }
        }
        return null;
    }

    @Override
    public Tree<E> add(Tree<E> node, Tree<E> subNode) {
        Tree<E> nodeAlt = root.findNode(node.value);
        if (nodeAlt == null || !nodeAlt.equals(node)) {
            return null;
        }

        Tree<E> anotherSubNode = root.findNode(subNode.value);
        if (anotherSubNode != null) {
            return anotherSubNode;
        } else {
            node.sons.add(subNode);
            root.modCount++;
            subNode.root = root;
            subNode.parent = node;
            return subNode;
        }
    }

    @Override
    public Tree<E> add(Tree<E> node, E value) {
        Tree<E> nodeAlt = root.findNode(node.value);
        if (nodeAlt == null || !nodeAlt.equals(node)) {
            return null;
        }
        Tree<E> subNode = root.findNode(value);
        if (subNode != null) {
            return subNode;
        } else {
            Tree<E> newNode = new Tree<>(value);
            node.sons.add(newNode);
            root.modCount++;
            newNode.root = root;
            newNode.parent = node;
            return newNode;
        }
    }

    @Override
    public Tree<E> add(Tree<E> node) {
        Tree<E> subNode = root.findNode(node.value);
        if (subNode != null) {
            return subNode;
        } else {
            this.sons.add(node);
            root.modCount++;
            node.root = root;
            node.parent = this;
            return node;
        }
    }

    @Override
    public Tree<E> add(E value) {
        Tree<E> subNode = root.findNode(value);
        if (subNode != null) {
            return subNode;
        } else if (root.value == null) {
            root.value = value;
            root.modCount++;
            return root;
        } else {
            Tree<E> newNode = new Tree<>(value);
            this.sons.add(newNode);
            root.modCount++;
            newNode.root = root;
            newNode.parent = this;
            return newNode;
        }
    }

    @Override
    public boolean remove(Tree<E> node) {
        Tree<E> foundNode = findNode(node.value);
        if (foundNode == null || !foundNode.equals(node)) {
            return false;
        } else {
            if (node == this.root) {
                root.modCount++;
                node.value = null;
            } else {
                node.parent.sons.addAll(node.sons);
                node.parent.sons.remove(node);
                for (Tree<E> son : node.sons) {
                    son.parent = node.parent;
                }
                root.modCount++;
            }
            return true;
        }
    }

    @Override
    public boolean remove(E value) {
        Tree<E> node = findNode(value);
        if (node == null) {
            return false;
        } else {
            remove(node);
            return true;
        }
    }

    public Iterator<Tree<E>> iterator(IteratorType mode) throws IllegalStateException {
        if (mode != null) {
            return new TreeIterator<>(mode, this);
        }
        throw new IllegalStateException();
    }

    public Iterator<Tree<E>> iterator() throws IllegalStateException {
        return new TreeIterator<>(IteratorType.DFS, this);
    }

    public void changeMode(IteratorType mode) {
        this.mode = mode;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public String printChildren() {
        StringBuilder nodeInfo = new StringBuilder(this.value + ": ");
        for (Tree<E> son : this.sons) {
            nodeInfo.append(son.value).append(" ");
        }
        return nodeInfo.toString();
    }

    @Override
    public String toString() {
        StringBuilder treeInfo = new StringBuilder();
        for (Tree<E> node : this) {
            treeInfo.append(node.printChildren()).append("\n");
        }
        return treeInfo.toString();
    }
}