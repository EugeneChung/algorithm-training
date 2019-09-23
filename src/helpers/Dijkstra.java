package helpers;

import java.util.*;

public class Dijkstra {
    static class Node implements Comparable<Node> {
        final int node;
        final int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private int[] distances;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private List<List<Node>> adj;
    private int[] parents;

    Dijkstra(int V) {
        distances = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V);
        parents = new int[V];
    }

    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1); //NO_PARENT

        // Add source node to the priority queue 
        pq.add(new Node(src, 0));

        // Distance to the source is 0 
        distances[src] = 0;
        while (settled.size() != distances.length) { // O((V + E) log V)
            // remove the minimum distance node from the priority queue  
            int u = pq.remove().node;

            // adding the node whose distance is finalized 
            settled.add(u);

            traverseNeighbours(u);
        }
    }

    // Function to process all the neighbours of the passed node 
    private void traverseNeighbours(int u) {
        // All the neighbors of v 
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed 
            if (!settled.contains(v.node)) {
                int newDistance = distances[u] + v.cost;
                if (newDistance < distances[v.node]) {
                    // If new distance is cheaper in cost
                    distances[v.node] = newDistance;
                    parents[v.node] = u;
                }

                // Add the current node to the queue 
                pq.add(new Node(v.node, distances[v.node]));
            }
        }
    }

    private void printPath(StringBuilder path, int pos) {
        if (parents[pos] == -1) {
            path.insert(0, pos);
            return;
        }
        path.insert(0, pos).insert(0, "->");
        printPath(path, parents[pos]);
    }

    public static void main(String arg[]) {
        int V = 7;

        // Adjacency list representation of the  
        // connected edges 
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every node 
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        // Inputs for the DPQ graph 
        adj.get(0).add(new Node(1, 5));
        adj.get(0).add(new Node(3, 21));

        adj.get(1).add(new Node(2, 40));

        adj.get(2).add(new Node(3, 13));
        adj.get(2).add(new Node(4, 19));

        adj.get(3).add(new Node(5, 41));

        adj.get(4).add(new Node(5, 32));
        adj.get(4).add(new Node(6, 14));

        adj.get(5).add(new Node(6, 8));

        int source = 0;

        // Calculate the single source shortest path 
        Dijkstra dpq = new Dijkstra(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes 
        // from the source node 
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.distances.length; i++)
            System.out.println(source + " to " + i + " is "
                + dpq.distances[i]);

        StringBuilder path = new StringBuilder();
        dpq.printPath(path, 6);
        System.out.println(path.toString());
    }
}
