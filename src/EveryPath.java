//Problem 5

import java.util.*;

public class EveryPath {
    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        int V;
        List<List<Edge>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
        }

        void findPaths(int u, int w, int length, List<Integer> path, int currentWeight) {

            path.add(u);

            if (u == w && path.size() == 7) {
                System.out.println(path);
            } else {
                if (path.size() >= 7) {
                    path.remove(path.size() - 1);
                    return;
                }

                for (Edge edge : adjList.get(u)) {
                    if (!path.contains(edge.destination)) {
                        findPaths(edge.destination, w, length, path, currentWeight + edge.weight);
                    }
                }
            }

            // Backtrack
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        Graph graph = new Graph(V);

        System.out.println("Enter the edges (source, destination, weight) - enter -1 -1 -1 to stop:");
        while (true) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();

            if (u == -1 && v == -1 && weight == -1) {
                break;
            }

            graph.addEdge(u, v, weight);
        }

        System.out.print("Enter the starting vertex (u): ");
        int u = scanner.nextInt();

        System.out.print("Enter the ending vertex (w): ");
        int w = scanner.nextInt();

        System.out.println("Simple paths from " + u + " to " + w + " with a length of 7:");
        graph.findPaths(u, w, 7, new ArrayList<>(), 0);

        scanner.close();
    }
}
