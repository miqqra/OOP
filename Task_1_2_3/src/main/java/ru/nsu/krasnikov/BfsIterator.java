package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * BFS iterator for a graph.
 *
 * @param <E> data type of vertex name.
 * @param <T> data type for vertex.
 */
public class BfsIterator<E, T extends Vertex<E>> implements Iterator<Vertex<E>> {
    private final Graph<E> graph;
    private final Queue<T> queue;
    private final int expectedModCount;
    private final HashMap<T, Vertex.IteratorColor> verticesColors;

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public BfsIterator(Graph<E> graph, T startNode) {
        queue = new ArrayDeque<>();
        expectedModCount = graph.getModCount();
        verticesColors = new HashMap<>();
        this.graph = graph;
        this.initialization();

        startNode.setVertexSearchParent(null);
        startNode.setVertexDistance(0);
        verticesColors.put(startNode, Vertex.IteratorColor.GRAY);
        queue.add(startNode);
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
        return !(queue.isEmpty());
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

        T vertex = queue.remove();
        verticesColors.put(vertex, Vertex.IteratorColor.GRAY);
        List<T> connectedVertice = (List<T>) vertex.getConnectedVertice();

        for (T connectedVertex : connectedVertice) {
            if (verticesColors.get(connectedVertex) == Vertex.IteratorColor.WHITE) {
                connectedVertex.setVertexSearchParent(vertex);
                connectedVertex.setVertexDistance(vertex.getVertexDistance() + 1);
                verticesColors.put(connectedVertex, Vertex.IteratorColor.GRAY);
                queue.add(connectedVertex);
            }
        }
        verticesColors.put(vertex, Vertex.IteratorColor.BLACK);
        return vertex;
    }
}



