package ru.nsu.krasnikov;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphTest {
    @Test
    public void testEdgeList() {
        GraphReader test = new GraphReader();
        Graph<String> graph = test.readGraph(
                "src\\test\\resources\\graph1.txt",
                GraphReader.WayOfRepresent.EDGE_LIST);
        Assertions.assertTrue(graph.hasVertex("a"));
        Assertions.assertTrue(graph.hasVertex("b"));
        Assertions.assertTrue(graph.hasVertex("c"));
        Assertions.assertTrue(graph.hasVertex("d"));
        Assertions.assertTrue(graph.hasVertex("e"));
        Assertions.assertTrue(graph.hasVertex("f"));
        Assertions.assertFalse(graph.hasVertex("g"));

        Assertions.assertTrue(graph.hasEdge("a", "b"));
        Assertions.assertTrue(graph.hasEdge("b", "c"));
        Assertions.assertTrue(graph.hasEdge("c", "d"));
        Assertions.assertTrue(graph.hasEdge("d", "e"));
        Assertions.assertTrue(graph.hasEdge("e", "f"));
        Assertions.assertTrue(graph.hasEdge("f", "a"));
        Assertions.assertFalse(graph.hasEdge("b", "a"));

        Assertions.assertEquals(graph.getEdgeWeight("a", "b"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("b", "c"), 2);
        Assertions.assertEquals(graph.getEdgeWeight("c", "d"), 3);
        Assertions.assertEquals(graph.getEdgeWeight("d", "e"), 4);
        Assertions.assertEquals(graph.getEdgeWeight("e", "f"), 5);
        Assertions.assertEquals(graph.getEdgeWeight("f", "a"), 6);
    }

    @Test
    public void testFunctions(){
        GraphReader test = new GraphReader();
        Graph<String> graph = test.readGraph(
                "src\\test\\resources\\graph1.txt",
                GraphReader.WayOfRepresent.EDGE_LIST);

        Assertions.assertEquals(graph.getMinWeightFromTo("a", "d"), 6);
        Assertions.assertEquals(graph.getMinWeightFromTo("e", "d"), 17);
        Assertions.assertEquals(graph.getMinWeightFromTo("a", "a"), 0);
        Assertions.assertTrue(graph.addEdge("a", "d", 2));
        Assertions.assertEquals(graph.getMinWeightFromTo("a", "d"), 2);
        Assertions.assertEquals(graph.getMinWeightFromTo("e", "d"), 13);

        Assertions.assertFalse(graph.hasVertex("k"));
        Assertions.assertTrue(graph.addVertex("k"));
        Assertions.assertTrue(graph.hasVertex("k"));

        Assertions.assertTrue(graph.addEdge("k", "a", 3));
        Assertions.assertTrue(graph.addEdge("k", "a", 7));
        Assertions.assertEquals(graph.getMinWeightFromTo("k", "b"), 4);
        Assertions.assertEquals(graph.getMinWeightFromTo("b", "k"), -1);

        List<Vertex<String>> vertices = graph.getVertices();
        Assertions.assertEquals(graph.findNode("a"), vertices.get(0));
        Assertions.assertFalse(graph.removeEdge("a", "f"));
        Assertions.assertTrue(graph.removeEdge("b", "c"));
        Assertions.assertFalse(graph.removeEdge("a", "t"));
        Assertions.assertFalse(graph.removeVertex("r"));

        Assertions.assertEquals(graph.getMinWeightFromTo("f", "e"), 12);
        Assertions.assertTrue(graph.setEdgeWeight("f", "a", 7));
        Assertions.assertEquals(graph.getMinWeightFromTo("f", "e"), 13);

        Assertions.assertTrue(graph.removeVertex("d"));
        Assertions.assertEquals(graph.getMinWeightFromTo("a", "f"), -1);
        Assertions.assertFalse(graph.hasVertex("d"));
        Assertions.assertFalse(graph.hasEdge("a", "d"));
        Assertions.assertFalse(graph.hasEdge("d", "e"));
        Assertions.assertFalse(graph.hasEdge("c", "d"));
    }

    @Test
    public void testRepresentGraph(){
        GraphReader test = new GraphReader();
        Graph<String> graph = test.readGraph(
                "src\\test\\resources\\graph1.txt",
                GraphReader.WayOfRepresent.EDGE_LIST);

        Assertions.assertEquals(
                graph.getShortestPath("a", "f"),
                "f 15<----e 10<----d 6<----c 3<----b 1<----a 0");

        Assertions.assertEquals(
                graph.sortedVerticesFrom("a"),
                "a(0) b(1) c(3) d(6) e(10) f(15) ");

        graph.setIteratorType(Graph.IteratorType.BFS);
        List<Vertex<String>> vertices = graph.getVertices();
        for (Vertex<String> stringVertex : vertices.get(0)) {
            Assertions.assertEquals(stringVertex.getVertexIterateColor(), Vertex.IteratorColor.BLACK);
        }
        for (int i = 0; i<vertices.size(); i++){
            Assertions.assertEquals(vertices.get(i).getVertexDistance(), i);
        }

        graph.setIteratorType(Graph.IteratorType.DFS);
        vertices = graph.getVertices();
        for (Vertex<String> stringVertex : vertices.get(0)) {
            Assertions.assertEquals(stringVertex.getVertexIterateColor(), Vertex.IteratorColor.BLACK);
        }
        for (int i = 0; i<vertices.size(); i++){
            Assertions.assertEquals(vertices.get(i).getVertexDistance(), i);
        }
    }

    @Test
    public void testAdjacencyList() {
        GraphReader test = new GraphReader();
        Graph<String> graph = test.readGraph(
                "src\\test\\resources\\graph2.txt",
                GraphReader.WayOfRepresent.ADJACENCY_LIST);
        Assertions.assertTrue(graph.hasVertex("a"));
        Assertions.assertTrue(graph.hasVertex("b"));
        Assertions.assertTrue(graph.hasVertex("c"));
        Assertions.assertTrue(graph.hasVertex("d"));
        Assertions.assertTrue(graph.hasVertex("e"));
        Assertions.assertTrue(graph.hasVertex("f"));
        Assertions.assertFalse(graph.hasVertex("g"));

        Assertions.assertTrue(graph.hasEdge("a", "b"));
        Assertions.assertTrue(graph.hasEdge("b", "c"));
        Assertions.assertTrue(graph.hasEdge("c", "d"));
        Assertions.assertTrue(graph.hasEdge("d", "e"));
        Assertions.assertTrue(graph.hasEdge("e", "f"));
        Assertions.assertTrue(graph.hasEdge("f", "a"));
        Assertions.assertFalse(graph.hasEdge("b", "a"));

        Assertions.assertEquals(graph.getEdgeWeight("a", "b"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("b", "c"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("c", "d"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("d", "e"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("e", "f"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("f", "a"), 1);
    }

    @Test
    public void testAdjacencyMatrix() {
        GraphReader test = new GraphReader();
        Graph<String> graph = test.readGraph(
                "src\\test\\resources\\graph3.txt",
                GraphReader.WayOfRepresent.ADJACENCY_MATRIX);
        Assertions.assertTrue(graph.hasVertex("a"));
        Assertions.assertTrue(graph.hasVertex("b"));
        Assertions.assertTrue(graph.hasVertex("c"));
        Assertions.assertTrue(graph.hasVertex("d"));
        Assertions.assertTrue(graph.hasVertex("e"));
        Assertions.assertTrue(graph.hasVertex("f"));
        Assertions.assertFalse(graph.hasVertex("g"));

        Assertions.assertTrue(graph.hasEdge("a", "b"));
        Assertions.assertTrue(graph.hasEdge("b", "c"));
        Assertions.assertTrue(graph.hasEdge("c", "d"));
        Assertions.assertTrue(graph.hasEdge("d", "e"));
        Assertions.assertTrue(graph.hasEdge("e", "f"));
        Assertions.assertTrue(graph.hasEdge("f", "a"));
        Assertions.assertFalse(graph.hasEdge("b", "a"));

        Assertions.assertEquals(graph.getEdgeWeight("a", "b"), 1);
        Assertions.assertEquals(graph.getEdgeWeight("b", "c"), 2);
        Assertions.assertEquals(graph.getEdgeWeight("c", "d"), 3);
        Assertions.assertEquals(graph.getEdgeWeight("d", "e"), 4);
        Assertions.assertEquals(graph.getEdgeWeight("e", "f"), 5);
        Assertions.assertEquals(graph.getEdgeWeight("f", "a"), 6);
    }
}
