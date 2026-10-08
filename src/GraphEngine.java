import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class GraphEngine {

    private int vertices;
    private List<List<Edge>> adjacencyList;
    private String[] vertexNames;

    // Represents a connection between two vertices
    private class Edge {

        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Used by Dijkstra's algorithm
    private class NodeDistance implements Comparable<NodeDistance> {

        int vertex;
        int distance;

        NodeDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public GraphEngine(String[] vertexNames) {

        this.vertexNames = vertexNames;
        this.vertices = vertexNames.length;

        adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // =========================================================
    // ADD EDGE
    // =========================================================

    public void addEdge(String source, String destination, int weight) {

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {

            System.out.println("Invalid vertex.");
            return;
        }

        adjacencyList
                .get(sourceIndex)
                .add(new Edge(destinationIndex, weight));

        adjacencyList
                .get(destinationIndex)
                .add(new Edge(sourceIndex, weight));
    }

    // =========================================================
    // BFS
    // =========================================================

    public void bfs(String startVertex) {

        int startIndex = getVertexIndex(startVertex);

        if (startIndex == -1) {
            System.out.println("Invalid starting vertex.");
            return;
        }

        boolean[] visited = new boolean[vertices];

        int[] queue = new int[vertices];

        int front = 0;
        int rear = 0;

        queue[rear++] = startIndex;
        visited[startIndex] = true;

        System.out.println("\n========== BFS TRAVERSAL ==========");

        while (front < rear) {

            int current = queue[front++];

            System.out.print(vertexNames[current] + " ");

            for (Edge edge : adjacencyList.get(current)) {

                if (!visited[edge.destination]) {

                    visited[edge.destination] = true;
                    queue[rear++] = edge.destination;
                }
            }
        }

        System.out.println();
    }

    // =========================================================
    // DFS
    // =========================================================

    public void dfs(String startVertex) {

        int startIndex = getVertexIndex(startVertex);

        if (startIndex == -1) {
            System.out.println("Invalid starting vertex.");
            return;
        }

        boolean[] visited = new boolean[vertices];

        System.out.println("\n========== DFS TRAVERSAL ==========");

        dfsRecursive(startIndex, visited);

        System.out.println();
    }

    private void dfsRecursive(
            int current,
            boolean[] visited) {

        visited[current] = true;

        System.out.print(vertexNames[current] + " ");

        for (Edge edge : adjacencyList.get(current)) {

            if (!visited[edge.destination]) {

                dfsRecursive(
                        edge.destination,
                        visited
                );
            }
        }
    }

    // =========================================================
    // DIJKSTRA SHORTEST PATH
    // =========================================================

    public void shortestPath(
            String source,
            String destination) {

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {

            System.out.println("Invalid source or destination.");
            return;
        }

        int[] distances = new int[vertices];

        int[] previous = new int[vertices];

        boolean[] visited = new boolean[vertices];

        Arrays.fill(
                distances,
                Integer.MAX_VALUE
        );

        Arrays.fill(previous, -1);

        distances[sourceIndex] = 0;

        PriorityQueue<NodeDistance> priorityQueue =
                new PriorityQueue<>();

        priorityQueue.offer(
                new NodeDistance(sourceIndex, 0)
        );

        while (!priorityQueue.isEmpty()) {

            NodeDistance current =
                    priorityQueue.poll();

            int currentVertex = current.vertex;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            for (Edge edge :
                    adjacencyList.get(currentVertex)) {

                int newDistance =
                        distances[currentVertex]
                        + edge.weight;

                if (newDistance <
                        distances[edge.destination]) {

                    distances[edge.destination] =
                            newDistance;

                    previous[edge.destination] =
                            currentVertex;

                    priorityQueue.offer(
                            new NodeDistance(
                                    edge.destination,
                                    newDistance
                            )
                    );
                }
            }
        }

        if (distances[destinationIndex] ==
                Integer.MAX_VALUE) {

            System.out.println(
                    "\nNo path exists between "
                    + source
                    + " and "
                    + destination
            );

            return;
        }

        System.out.println(
                "\n========== SHORTEST PATH =========="
        );

        System.out.println(
                "Source      : " + source
        );

        System.out.println(
                "Destination : " + destination
        );

        System.out.println(
                "Distance    : "
                + distances[destinationIndex]
        );

        System.out.print("Path        : ");

        printPath(
                destinationIndex,
                previous
        );

        System.out.println();
    }

    private void printPath(
            int vertex,
            int[] previous) {

        if (vertex == -1) {
            return;
        }

        printPath(
                previous[vertex],
                previous
        );

        System.out.print(
                vertexNames[vertex]
                + " "
        );
    }

    // =========================================================
    // DISPLAY GRAPH
    // =========================================================

    public void displayGraph() {

        System.out.println(
                "\n========== GRAPH VISUALIZATION =========="
        );

        for (int i = 0; i < vertices; i++) {

            System.out.print(
                    vertexNames[i] + " -> "
            );

            for (Edge edge :
                    adjacencyList.get(i)) {

                System.out.print(
                        vertexNames[edge.destination]
                        + "("
                        + edge.weight
                        + ") "
                );
            }

            System.out.println();
        }
    }

    // =========================================================
    // VERTEX SEARCH
    // =========================================================

    private int getVertexIndex(String name) {

        for (int i = 0; i < vertices; i++) {

            if (vertexNames[i]
                    .equalsIgnoreCase(name)) {

                return i;
            }
        }

        return -1;
    }

    public int getVertexCount() {
        return vertices;
    }
}