package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
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

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public DijkstraIterator(Graph<E> graph, T startNode) {
        array = new ArrayList<>();
        expectedModCount = graph.getModCount();
        this.graph = graph;
        this.initialization();

        startNode.setVertexSearchParent(null);
        startNode.setVertexWeight(0);
        startNode.setVertexIterateColor(Vertex.IteratorColor.GRAY);
        array.add(startNode);
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
        for (Edge edge : vertex.edgesStarts) {
            T neighbour = (T) edge.getEdgeEnd();
            if (edge.getEdgeWeight() + vertex.getVertexWeight()
                    < neighbour.getVertexWeight()) {
                neighbour.setVertexWeight(edge.getEdgeWeight() + vertex.getVertexWeight());
                neighbour.setVertexSearchParent(vertex);
                if (neighbour.getVertexIterateColor() == Vertex.IteratorColor.WHITE) {
                    neighbour.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                    array.add(neighbour);
                }
            }
        }
        array.remove(vertex);
        return vertex;

    }
}



