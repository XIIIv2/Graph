package app;

public class Main {

    private static final Graph graph = new Graph();

    public static void main(String[] args) {

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        try {
            graph.addEdge(1, 5);
            graph.addEdge(1, 3);
            graph.addEdge(5, 2);
            graph.addEdge(3, 2);
            graph.addEdge(5, 4);
            graph.addEdge(3, 4);
            graph.addEdge(6, 4);
            graph.addEdge(1, 6);
            graph.addEdge(6, 3);
            graph.addEdge(2, 6);

            // ребра с несуществуюми вершинами
            graph.addEdge(7, 8);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("-------------------------------------");
        }

        printGraph();
        System.out.println("-------------------------------------");

        checkVertex(6);
        System.out.println("Удаляем вершину 6");
        graph.removeVertex(6);
        printGraph();
        checkVertex(6);
        System.out.println("-------------------------------------");

        checkEdge(3, 4);
        System.out.println("Удаляем ребра [3:4]");
        graph.removeEdge(3 ,4);
        printGraph();
        checkEdge(3, 4);
    }

    private static void checkVertex(int vertex) {
        if (graph.hasVertex(vertex)) {
            System.out.println("Граф содержит вершину " + vertex);
        } else {
            System.out.println("Граф не содержит вершину " + vertex);
        }
    }

    private static void checkEdge(int source, int destination) {
        if (graph.hasEdge(source, destination)) {
            System.out.println("Граф содержит ребра [" + source + ":" + destination + "]");
        } else {
            System.out.println("Граф не содержит ребра [" + source + ":" + destination + "]");
        }
    }

    private static void printGraph() {
        graph.getVertices().forEach((k, v) -> {
            System.out.println("Вершина " + k + " -> " + v.toString());
        });
    }
}
