package ru.nsu.krasnikov;

/**
 * edge class for interaction with edges in graph.
 */
public class Edge {
    private int weight;
    private final Vertex<?> fromNode;
    private final Vertex<?> toNode;

    /**
     * create edge.
     *
     * @param weight     weight of the edge.
     * @param fromVertex vertex-start of an edge.
     * @param toVertex   vertex-end of an edge.
     */
    public Edge(int weight, Vertex<?> fromVertex, Vertex<?> toVertex) {
        this.weight = weight;
        this.fromNode = fromVertex;
        this.toNode = toVertex;
    }

    /**
     * get edge weight.
     *
     * @return edge weight.
     */
    public int getEdgeWeight() {
        return this.weight;
    }

    /**
     * set edge weight.
     *
     * @param weight new weight.
     */
    public void setEdgeWeight(int weight) {
        this.weight = weight;
    }

    /**
     * get vertex-start of an edge.
     *
     * @return vertex-start of an edge.
     */
    public Vertex<?> getEdgeStart() {
        return this.fromNode;
    }

    /**
     * get vertex-end of an edge.
     *
     * @return vertex-end of an edge.
     */
    public Vertex<?> getEdgeEnd() {
        return this.toNode;
    }
}
