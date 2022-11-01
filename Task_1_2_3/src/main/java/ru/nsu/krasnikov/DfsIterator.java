package ru.nsu.krasnikov;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
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
public class DfsIterator<E, T extends Vertex<E>> implements Iterator<Vertex<E>> {
    private final Graph<E> graph;
    private final Stack<T> stack;
    private final int expectedModCount;
    private final HashMap<T, Vertex.IteratorColor> verticesColors;

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public DfsIterator(Graph<E> graph, T startNode) {
        stack = new Stack<>();
        expectedModCount = graph.getModCount();
        verticesColors = new HashMap<>();
        this.graph = graph;
        this.initialization();

        startNode.setVertexSearchParent(null);
        startNode.setVertexDistance(0);
        verticesColors.put(startNode, Vertex.IteratorColor.GRAY);
        stack.push(startNode);
    }

    @SuppressWarnings("unchecked")
    private void initialization() {
        for (Vertex<E> vertex : graph.getVertices()) {
            verticesColors.put((T) vertex, Vertex.IteratorColor.WHITE);
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
        verticesColors.put(vertex, Vertex.IteratorColor.GRAY);
        List<T> connectedVertice = (List<T>) vertex.getConnectedVertice();
        for (T connectedVertex : connectedVertice) {
            if (verticesColors.get(connectedVertex) == Vertex.IteratorColor.WHITE) {
                connectedVertex.setVertexSearchParent(vertex);
                connectedVertex.setVertexDistance(vertex.getVertexDistance() + 1);
                verticesColors.put(connectedVertex, Vertex.IteratorColor.GRAY);
                stack.push(connectedVertex);
            }
        }
        verticesColors.put(vertex, Vertex.IteratorColor.BLACK);
        return vertex;
    }
}



