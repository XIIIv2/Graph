package app;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Graph {

    private final Map<Integer, List<Integer>> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    /**
     * Возвращает неизменяемое представление списка смежности графа.
     */
    public Map<Integer, List<Integer>> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    /**
     * Добавляет вершину
     * @param vertex
     */
    public void addVertex(int vertex) {
        vertices.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Удаляет вершину и все ссылки на нее
     * @param vertex
     */
    public void removeVertex(int vertex) {
        if (!vertices.containsKey(vertex)) {
            return;
        }
        vertices.values()
                .forEach(edges -> edges.removeIf(e -> e == vertex));

        vertices.remove(vertex);
    }

    /**
     * Проверяет существование вершины
     * @param vertex
     * @return true если вершина существует, иначе false
     */
    public boolean hasVertex(int vertex) {
        return vertices.containsKey(vertex);
    }

    /**
     * Добавляет ребро между вершинами.
     * @throws IllegalArgumentException если вершины не существуют
     */
    public void addEdge(int source, int destination) throws IllegalArgumentException {
        if (!hasVertex(source) || !hasVertex(destination)) {
            throw new IllegalArgumentException("Обе вершины должны существовать в графе. [" + source + ":" + destination + "]");
        }
        vertices.get(source).add(destination);
        vertices.get(destination).add(source);
    }

    /**
     * Удаляет ребро между вершинами.
     * @param source
     * @param destination
     */
    public void removeEdge(int source, int destination) {
        if (hasVertex(source)) {
            vertices.get(source)
                    .removeIf(e -> e == destination);
        }

        if (hasVertex(destination)) {
            vertices.get(destination)
                    .removeIf(e -> e == source);
        }
    }

    /**
     * Проверяет существование ребра между вершинами.
     * @param source
     * @param destination
     * @return true если ребро существует, иначе false
     */
    public boolean hasEdge(int source, int destination) {
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }
        return vertices.get(source).contains(destination) &&
                vertices.get(destination).contains(source);
    }
}
