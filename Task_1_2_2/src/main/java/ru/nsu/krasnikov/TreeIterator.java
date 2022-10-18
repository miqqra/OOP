package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Class for custom tree iterator. Tree can be iterated two ways, breadth and depth search.
 *
 * @param <T> extends Tree of any type of values.
 */
public class TreeIterator<T extends Tree<?>> implements Iterator<T> {
    private final Stack<T> stack;
    private final Queue<T> queue;
    private final Tree.IteratorType mode;
    private final T root;
    private final int expectedModCount;

    /**
     * Class for iterating tree. Has needed functions next and hasNext.
     *
     * @param mode      BFS or DFS, ways for iterating tree.
     * @param startNode root of the tree, that will be iterated.
     * @throws IllegalStateException if mode neither BFS, nor DFS.
     */
    public TreeIterator(Tree.IteratorType mode, T startNode) throws IllegalStateException {
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