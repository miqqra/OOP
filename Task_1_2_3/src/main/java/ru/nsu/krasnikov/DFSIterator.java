package ru.nsu.krasnikov;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * DFS iterator for a graph.
 *
 * @param <E> data type of vertex name.
 * @param <T> data type for vertex.
 */
public class DFSIterator<E, T extends Vertex<E>> implements Iterator<Vertex<E>> {
    private final Graph<E> graph;
    private final Stack<T> stack;
    private final int expectedModCount;

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public DFSIterator(Graph<E> graph, T startNode) {
        stack = new Stack<>();
        expectedModCount = graph.getModCount();
        this.graph = graph;
        this.initialization();

        startNode.setVertexSearchParent(null);
        startNode.setVertexDistance(0);
        startNode.setVertexIterateColor(Vertex.IteratorColor.GRAY);
        stack.push(startNode);
    }

    private void initialization() {
        for (Vertex<E> vertex : graph.getVertices()) {
            vertex.setVertexIterateColor(Vertex.IteratorColor.WHITE);
            vertex.setVertexDistance(Integer.MAX_VALUE);
            vertex.setVertexSearchParent(null);
            vertex.setVertexWeight(Integer.MAX_VALUE);
        }
    }

    @Override
    public boolean hasNext() {
        return !(stack.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
        int curModCount = graph.getModCount();
        if (curModCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        T vertex = stack.pop();
        vertex.setVertexIterateColor(Vertex.IteratorColor.GRAY);
        List<T> connectedVertice = (List<T>) vertex.getConnectedVertice();
        for (T connectedVertex : connectedVertice) {
            if (connectedVertex.getVertexIterateColor() == Vertex.IteratorColor.WHITE) {
                connectedVertex.setVertexSearchParent(vertex);
                connectedVertex.setVertexDistance(vertex.getVertexDistance() + 1);
                connectedVertex.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                stack.push(connectedVertex);
            }
        }
        vertex.setVertexIterateColor(Vertex.IteratorColor.BLACK);
        return vertex;
    }
}



