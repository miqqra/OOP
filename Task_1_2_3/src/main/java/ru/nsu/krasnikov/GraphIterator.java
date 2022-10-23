package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * iterator for a graph with different iterator cases.
 *
 * @param <E> data type of vertex name.
 * @param <T> data type for vertex.
 */
public class GraphIterator<E, T extends Vertex<E>> implements Iterator<Vertex<E>> {
    private final Graph<E> graph;
    private final Graph.IteratorType iteratorType;
    private final Stack<T> stack;
    private final Queue<T> queue;
    private final List<T> array;
    private final int expectedModCount;

    /**
     * create iterator for a graph.
     *
     * @param graph     graph.
     * @param startNode first node for iterator.
     */
    public GraphIterator(Graph<E> graph, T startNode) {
        this.iteratorType = graph.getIteratorType();
        stack = new Stack<>();
        queue = new ArrayDeque<>();
        array = new ArrayList<>();
        expectedModCount = graph.getModCount();
        this.graph = graph;
        this.initialization();

        switch (iteratorType) {
            case DFS: {
                startNode.setVertexSearchParent(null);
                startNode.setVertexDistance(0);
                startNode.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                stack.push(startNode);
                break;
            }
            case BFS: {
                startNode.setVertexSearchParent(null);
                startNode.setVertexDistance(0);
                startNode.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                queue.add(startNode);
                break;
            }
            case DIJKSTRA: {
                startNode.setVertexSearchParent(null);
                startNode.setVertexWeight(0);
                startNode.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                array.add(startNode);
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
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
        switch (iteratorType) {
            case DFS: {
                return !(stack.isEmpty());
            }
            case BFS: {
                return !(queue.isEmpty());
            }
            case DIJKSTRA: {
                return !(array.isEmpty());
            }
            default: {
                throw new IllegalStateException();
            }
        }
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

        switch (iteratorType) {
            case BFS: {
                T vertex = queue.remove();
                vertex.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                List<T> connectedVertice = (List<T>) vertex.getConnectedVertice();
                for (T connectedVertex : connectedVertice) {
                    if (connectedVertex.getVertexIterateColor() == Vertex.IteratorColor.WHITE) {
                        connectedVertex.setVertexSearchParent(vertex);
                        connectedVertex.setVertexDistance(vertex.getVertexDistance() + 1);
                        connectedVertex.setVertexIterateColor(Vertex.IteratorColor.GRAY);
                        queue.add(connectedVertex);
                    }
                }
                vertex.setVertexIterateColor(Vertex.IteratorColor.BLACK);
                return vertex;
            }
            case DFS: {
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
            case DIJKSTRA: {
                T vertex = Collections.min(array);
                for (Edge edge : vertex.edgesStarts) {
                    T neighbour = (T) edge.getEdgeEnd();
                    if (edge.getEdgeWeight() + vertex.getVertexWeight() < neighbour.getVertexWeight()) {
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
            default: {
                throw new IllegalStateException();
            }
        }
    }
}


