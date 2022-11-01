package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * dijkstra iterator for a graph.
 *
 * @param <E> data type of vertex name.
 * @param <T> data type for vertex.
 */
public class DijkstraIterator<E, T extends Vertex<E>> implements Iterator<Vertex<E>> {
    private final Graph<E> graph;
    private final List<T> array;
    private final int expectedModCount;
    private final HashMap<T, Vertex.IteratorColor> verticesColors;

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public DijkstraIterator(Graph<E> graph, T startNode) {
        array = new ArrayList<>();
        expectedModCount = graph.getModCount();
        verticesColors = new HashMap<>();
        this.graph = graph;
        this.initialization();

        startNode.setVertexSearchParent(null);
        startNode.setVertexWeight(0);
        verticesColors.put(startNode, Vertex.IteratorColor.GRAY);
        array.add(startNode);
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
        return !(array.isEmpty());
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

        T vertex = Collections.min(array);
        for (Edge edge : vertex.getEdgesStarts()) {
            T neighbour = (T) edge.getEdgeEnd();
            if (edge.getEdgeWeight() + vertex.getVertexWeight()
                    < neighbour.getVertexWeight()) {
                neighbour.setVertexWeight(edge.getEdgeWeight() + vertex.getVertexWeight());
                neighbour.setVertexSearchParent(vertex);
                if (verticesColors.get(neighbour) == Vertex.IteratorColor.WHITE) {
                    verticesColors.put(neighbour, Vertex.IteratorColor.GRAY);
                    array.add(neighbour);
                }
            }
        }
        array.remove(vertex);
        return vertex;

    }
}



