package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Vertex class for interaction with vertices from graph.
 *
 * @param <E> data type of vertex name.
 */
public class Vertex<E> implements Comparable<Vertex<E>>, Iterable<Vertex<E>> {
    Graph<E> graph;
    List<Edge> edgesStarts;
    List<Edge> edgesEnds;
    private final E vertexName;
    private IteratorColor vertexIterateColor;
    private int vertexWeight;
    private int vertexDistance;
    private Vertex<E> searchParent;

    /**
     * create vertex.
     *
     * @param vertexName vertex name.
     * @param graph      vertex will be part of that graph.
     */
    public Vertex(E vertexName, Graph<E> graph) {
        this.vertexName = vertexName;
        edgesStarts = new ArrayList<>();
        edgesEnds = new ArrayList<>();
        vertexIterateColor = IteratorColor.WHITE;
        vertexWeight = 0;
        this.graph = graph;
    }

    /**
     * removes vertex and all incident edges from a graph.
     *
     * @return true if vertex has been removed, false otherwise.
     */
    public boolean removeVertex() {
        for (Edge edge : this.edgesStarts) {
            edge.getEdgeEnd().edgesEnds.remove(edge);
        }
        for (Edge edge : this.edgesEnds) {
            edge.getEdgeStart().edgesStarts.remove(edge);
        }
        return true;
    }

    /**
     * removes edge between this and toVertex.
     *
     * @param toVertex vertex-end of the edge.
     * @return true if edge has been removed, false otherwise.
     */
    public boolean removeEdgeWith(Vertex<E> toVertex) {
        for (Edge edge : this.edgesStarts) {
            if (edge.getEdgeEnd() == toVertex) {
                this.edgesStarts.remove(edge);
                edge.getEdgeEnd().edgesEnds.remove(edge);
                return true;
            }
        }
        return false;
    }

    /**
     * get vertex name.
     *
     * @return vertex name.
     */
    public E getVertexName() {
        return this.vertexName;
    }

    /**
     * set vertex weight.
     *
     * @param weight new weight.
     */
    public void setVertexWeight(int weight) {
        this.vertexWeight = weight;
    }

    /**
     * get vertex weight.
     *
     * @return vertex weight.
     */
    public int getVertexWeight() {
        return this.vertexWeight;
    }

    /**
     * get parent of the vertex.
     *
     * @return parent of the vertex.
     */
    public Vertex<E> getSearchParent() {
        return this.searchParent;
    }

    /**
     * get vertices connected with edges with this vertex.
     *
     * @return connected vertices.
     */
    @SuppressWarnings("unchecked")
    public List<Vertex<E>> getConnectedVertice() {
        List<Vertex<E>> connectedVertice = new ArrayList<>();
        for (Edge edge : edgesStarts) {
            connectedVertice.add((Vertex<E>) edge.getEdgeEnd());
        }
        return connectedVertice;
    }

    void setVertexIterateColor(IteratorColor color) {
        this.vertexIterateColor = color;
    }

    /**
     * get vertex distance.
     *
     * @return vertex distance.
     */
    public int getVertexDistance() {
        return this.vertexDistance;
    }

    void setVertexDistance(int distance) {
        this.vertexDistance = distance;
    }

    @SuppressWarnings("unchecked")
    void setVertexSearchParent(Vertex<?> vertex) {
        this.searchParent = (Vertex<E>) vertex;
    }

    protected IteratorColor getVertexIterateColor() {
        return this.vertexIterateColor;
    }

    /**
     * compares two objects.
     *
     * @param o the object to be compared.
     * @return -1 of this less, 0 equals, 1 greater.
     */
    @Override
    public int compareTo(Vertex<E> o) {
        return Integer.compare(this.vertexWeight, o.vertexWeight);
    }

    /**
     * iterate color variables.
     */
    enum IteratorColor {
        WHITE, GRAY, BLACK
    }

    /**
     * iterator graph.
     *
     * @return graph iterator.
     */
    public Iterator<Vertex<E>> iterator() {
        return new GraphIterator<>(graph, this);
    }
}
