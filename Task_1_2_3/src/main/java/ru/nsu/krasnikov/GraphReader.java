package ru.nsu.krasnikov;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * class for reading information about graph from file.
 */
public class GraphReader {
    private static final int VERTEX_WEIGHT = 1;

    /**
     * ways to represent graph in file.
     */
    public enum WayOfRepresent {
        ADJACENCY_LIST, ADJACENCY_MATRIX, EDGE_LIST
    }

    /**
     * reads graph from file.
     *
     * @param fileName       file name.
     * @param wayOfRepresent how info about graph is written in file.
     * @return graph object with vertices and edges from file.
     */
    public Graph<String> readGraph(String fileName, WayOfRepresent wayOfRepresent) {
        Graph<String> graph = new Graph<>();
        File file = new File(fileName);
        Scanner r;
        try {
            r = new Scanner(file);
            String[] verticesNames = r.nextLine().split(" ");
            for (String elem : verticesNames) {
                graph.addVertex(elem);
            }
            switch (wayOfRepresent) {
                case ADJACENCY_LIST: {
                    for (String name : verticesNames) {
                        String[] data = (r.nextLine()).split(",");
                        for (String vertex : data) {
                            graph.addEdge(name, vertex, VERTEX_WEIGHT);
                        }
                    }
                    break;
                }
                case ADJACENCY_MATRIX: {
                    for (String name : verticesNames) {
                        String[] data = r.nextLine().split(" ");
                        for (int i = 0; i < data.length; i++) {
                            if (Integer.parseInt(data[i]) > 0) {
                                graph.addEdge(name, verticesNames[i], Integer.parseInt(data[i]));
                            }
                        }
                    }
                    break;
                }
                case EDGE_LIST: {
                    while (r.hasNextLine()) {
                        String[] data = r.nextLine().split(" ");
                        graph.addEdge(data[0], data[1], Integer.parseInt(data[2]));
                    }
                    break;
                }
                default:
                    throw new IllegalStateException();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }
}
