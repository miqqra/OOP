package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * main class for interaction with simple graph with positive weights edges.
 *
 * @param <E> vertex name data type.
 */
public class Graph<E> implements Iterable<Vertex<E>> {
    private final List<Vertex<E>> vertices;
    private IteratorType iteratorType;
    private int modCount;
    private Vertex<E> iterateVertex;

    /**
     * create graph.
     */
    public Graph() {
        iteratorType = IteratorType.DIJKSTRA;
        modCount = 0;
        vertices = new ArrayList<>();
    }

    /**
     * find vertex with its name.
     *
     * @param vertexName vertex name.
     * @return vertex object.
     */
    public Vertex<E> findNode(E vertexName) {
        for (Vertex<E> vertex : vertices) {
            if (vertexName.equals(vertex.getVertexName())) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * add vertex to a graph.
     *
     * @param vertexName name of the vertex, that will be added.
     * @return true if vertex has been added, false otherwise.
     */
    public boolean addVertex(E vertexName) {
        Vertex<E> newVertex = new Vertex<>(vertexName);
        modCount++;
        vertices.add(newVertex);
        return true;
    }

    /**
     * checks if edge in graph.
     *
     * @param fromVertexName vertex start of edge.
     * @param toVertexName   vertex end of edge.
     * @return true if edge in graph, false otherwise.
     */
    public boolean hasEdge(E fromVertexName, E toVertexName) {
        for (Vertex<E> vertex : vertices) {
            if (fromVertexName.equals(vertex.getVertexName())) {
                for (Edge edge : vertex.getEdgesStarts()) {
                    if (toVertexName.equals(edge.getEdgeEnd().getVertexName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if vertex in graph.
     *
     * @param vertexName name of vertex.
     * @return true if vertex in graph, false otherwise.
     */
    public boolean hasVertex(E vertexName) {
        for (Vertex<E> vertex : vertices) {
            if (vertexName.equals(vertex.getVertexName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove vertex and its incident edges from a graph.
     *
     * @param vertexName name of the vertex.
     * @return true if vertex has been found and deleted, false otherwise.
     */
    public boolean removeVertex(E vertexName) {
        Vertex<E> vertex = findNode(vertexName);
        modCount++;
        if (vertex == null) {
            return false;
        }
        modCount += vertex.getEdgesStarts().size();
        modCount += vertex.getEdgedEnds().size();
        vertices.remove(vertex);
        return vertex.removeVertex();
    }

    /**
     * get Edge object from graph.
     *
     * @param fromVertexName name of the vertex, that is start of edge.
     * @param toVertexName   name of the vertex, that is end of edge.
     * @return Edge object.
     */
    public Edge getEdge(E fromVertexName, E toVertexName) {
        Vertex<E> fromVertex = findNode(fromVertexName);
        Vertex<E> toVertex = findNode(toVertexName);
        for (Edge edge : fromVertex.getEdgesStarts()) {
            if (edge.getEdgeEnd() == toVertex) {
                return edge;
            }
        }
        return null;
    }

    /**
     * adds edge to a graph.
     *
     * @param fromVertexName name of the vertex, that is start of edge.
     * @param toVertexName   name of the vertex, that is end of edge.
     * @param weight         weight of new edge.
     * @return true if edge has been added, false otherwise.
     */
    public boolean addEdge(E fromVertexName, E toVertexName, int weight) {
        modCount++;
        Vertex<E> fromVertex = findNode(fromVertexName);
        Vertex<E> toVertex = findNode(toVertexName);
        Edge edge = new Edge(weight, fromVertex, toVertex);
        fromVertex.addEdgeStart(edge);
        toVertex.addEdgeEnd(edge);
        return true;
    }

    /**
     * set new weight to an edge.
     *
     * @param fromVertexName vertex-start of edge.
     * @param toVertexName   vertex-end of edge.
     * @param newWeight      new weight for chosen edge.
     * @return true if weight has been changed, false otherwise.
     */
    public boolean setEdgeWeight(E fromVertexName, E toVertexName, int newWeight) {
        for (Vertex<E> vertex : vertices) {
            if (fromVertexName.equals(vertex.getVertexName())) {
                for (Edge edge : vertex.getEdgesStarts()) {
                    if (toVertexName.equals(edge.getEdgeEnd().getVertexName())) {
                        edge.setEdgeWeight(newWeight);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * get edge weight.
     *
     * @param fromVertexName vertex-start of edge.
     * @param toVertexName   vertex-end of edge.
     * @return edge weight.
     */
    public int getEdgeWeight(E fromVertexName, E toVertexName) {
        return getEdge(fromVertexName, toVertexName).getEdgeWeight();
    }

    /**
     * remove edge from a graph.
     *
     * @param fromVertexName vertex-start of edge.
     * @param toVertexName   vertex-end of edge.
     * @return true if edge has been removed, false otherwise.
     */
    public boolean removeEdge(E fromVertexName, E toVertexName) {
        modCount++;
        Vertex<E> fromVertex = findNode(fromVertexName);
        Vertex<E> toVertex = findNode(toVertexName);
        return fromVertex.removeEdgeWith(toVertex);
    }

    /**
     * get path between two vertices.
     *
     * @param fromVertexName vertex start of path.
     * @param toVertexName   vertex end of path.
     * @return path between two vertices.
     */
    public String getShortestPath(E fromVertexName, E toVertexName) {
        Vertex<E> fromVertex = findNode(fromVertexName);
        StringBuilder path = new StringBuilder();
        this.setIteratorType(IteratorType.DIJKSTRA);
        this.setIterateVertex(fromVertex);
        for (Vertex<E> ignored : this) {
        }
        Vertex<E> curVertex = findNode(toVertexName);
        while (curVertex != fromVertex) {
            path.append(curVertex.getVertexName())
                    .append(" ")
                    .append(curVertex.getVertexWeight())
                    .append("<----");
            curVertex = curVertex.getSearchParent();
        }
        path.append(fromVertex.getVertexName())
                .append(" ")
                .append(fromVertex.getVertexWeight());
        return path.toString();
    }

    /**
     * Gets minimal weight of path between two vertices.
     *
     * @param fromVertexName vertex start of a path.
     * @param toVertexName   vertex end of a path.
     * @return minimal weight, if there is a path between these vertices, -1 otherwise.
     */
    public int getMinWeightFromTo(E fromVertexName, E toVertexName) {
        this.setIteratorType(IteratorType.DIJKSTRA);
        this.setIterateVertex(fromVertexName);
        for (Vertex<E> ignored : this) {

        }
        Vertex<E> toVertex = findNode(toVertexName);
        return (toVertex.getVertexWeight() == Integer.MAX_VALUE ? -1 : toVertex.getVertexWeight());
    }

    /**
     * get sorted list of vertices with weights of paths from chosen vertex.
     *
     * @param fromVertexName vertex, from that will be built all paths.
     * @return sorted list of vertices with its path weights.
     */
    public String sortedVerticesFrom(E fromVertexName) {
        List<Vertex<E>> vertices = new ArrayList<>();
        this.setIteratorType(IteratorType.DIJKSTRA);
        this.setIterateVertex(fromVertexName);
        for (Vertex<E> vertex : this) {
            vertices.add(vertex);
        }
        Collections.sort(vertices);
        StringBuilder vertexInfo = new StringBuilder();
        for (Vertex<E> vertex : vertices) {
            if (vertex.getVertexWeight() == Integer.MAX_VALUE) {
                vertexInfo
                        .append(vertex.getVertexName().toString())
                        .append("(Infinity) ");
            } else {
                vertexInfo
                        .append(vertex.getVertexName().toString())
                        .append("(")
                        .append(vertex.getVertexWeight())
                        .append(") ");
            }
        }
        return vertexInfo.toString();
    }

    /**
     * get all vertices from a graph.
     *
     * @return list of vertices.
     */
    public List<Vertex<E>> getVertices() {
        return this.vertices;
    }

    /**
     * get changes count.
     *
     * @return changes count.
     */
    public int getModCount() {
        return modCount;
    }

    /**
     * change type of iteration.
     *
     * @param iteratorType way to iterate graph.
     */
    public void setIteratorType(IteratorType iteratorType) {
        this.iteratorType = iteratorType;
    }

    /**
     * set start node for iterating in graph.
     *
     * @param vertex start node.
     */
    public void setIterateVertex(Vertex<E> vertex) {
        this.iterateVertex = vertex;
    }

    /**
     * set start node for iterating in graph.
     *
     * @param vertexName name of start node.
     */
    public void setIterateVertex(E vertexName) {
        this.iterateVertex = findNode(vertexName);
    }

    @Override
    public Iterator<Vertex<E>> iterator() {
        if (iterateVertex == null) {
            throw new IllegalStateException();
        }

        switch (iteratorType) {
            case DFS: {
                return new DfsIterator<>(this, iterateVertex);
            }
            case BFS: {
                return new BfsIterator<>(this, iterateVertex);
            }
            case DIJKSTRA: {
                return new DijkstraIterator<>(this, iterateVertex);
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * ways to iterate graph.
     */
    public enum IteratorType {
        BFS, DFS, DIJKSTRA
    }
}
