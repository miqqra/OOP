package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex class for interaction with vertices from graph.
 *
 * @param <E> data type of vertex name.
 */
public class Vertex<E> implements Comparable<Vertex<E>> {
    private final List<Edge> edgesStarts;
    private final List<Edge> edgesEnds;
    private final E vertexName;
    private int vertexWeight;
    private int vertexDistance;
    private Vertex<E> searchParent;

    /**
     * create vertex.
     *
     * @param vertexName vertex name.
     */
    public Vertex(E vertexName) {
        this.vertexName = vertexName;
        edgesStarts = new ArrayList<>();
        edgesEnds = new ArrayList<>();
        vertexWeight = 0;
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

    /**
     * get vertex distance.
     *
     * @return vertex distance.
     */
    public int getVertexDistance() {
        return this.vertexDistance;
    }

    /**
     * get edges, that starts from this vertex.
     *
     * @return list of edges.
     */
    protected List<Edge> getEdgesStarts() {
        return this.edgesStarts;
    }

    protected List<Edge> getEdgedEnds() {
        return this.edgesEnds;
    }

    protected void addEdgeStart(Edge edge) {
        this.edgesStarts.add(edge);
    }

    protected void addEdgeEnd(Edge edge) {
        this.edgesEnds.add(edge);
    }

    void setVertexDistance(int distance) {
        this.vertexDistance = distance;
    }

    void setVertexSearchParent(Vertex<E> vertex) {
        this.searchParent = vertex;
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
}
