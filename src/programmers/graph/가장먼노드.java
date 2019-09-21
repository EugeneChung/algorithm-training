package programmers.graph;

import helpers.TestHelper;

import java.util.*;

public class 가장먼노드 {
    public static void main(String[] args) {
        int n = 6; int[][] edge = {{3, 6}, {4, 3}, {3,2},{1,3},{1,2},{2,4},{5,2}}; //3
//        int n = 4; int[][] edge = {{1,2},{2, 3},{3,4}}; //1
//        int n = 5; int[][] edge = {{1,2},{2, 3},{3,4},{3,5}}; //2
//        int n = 4; int[][] edge = {{1,2},{2,3},{3,4},{2,4}}; //2
//        int n = 8; int[][] edge = {{1,2},{2,3},{3,4},{2,4},{1,5},{3,5},{5, 6},{6, 7},{7,8},{1,8}}; //4
//        int n = 2; int[][] edge = {{1,1},{1,2}}; //1
//        int n = 4; int[][] edge = {{1,2},{3,4}}; //1

        TestHelper.printSolution(new Solution().solution(n, edge));
    }

    static class Solution {
        class Node {
            final int id;
            final Map<Integer, Node> neighborMap = new HashMap<>();

            public Node(int id) {
                this.id = id;
            }

            void addNeighbor(Node neighbor) {
                if (neighbor.id != id) {
                    neighborMap.put(neighbor.id, neighbor);
                }
            }
        }

        public int solution(int n, int[][] edge) {
            Node[] nodes = new Node[n + 1];
            for (int i = 0; i <= n; i++) {
                nodes[i] = new Node(i);
            }

            for (int[] e : edge) {
                nodes[e[0]].addNeighbor(nodes[e[1]]);
                nodes[e[1]].addNeighbor(nodes[e[0]]);
            }

            int[] distances = new int[nodes.length];
            Arrays.fill(distances, -1);
            Queue<Node> bfsQueue = new ArrayDeque<>(nodes.length);
            bfsQueue.add(nodes[1]);
            distances[1] = 0;

            while (!bfsQueue.isEmpty()) {
                Node nodeVisited = bfsQueue.poll();
                for (Node neighbor : nodeVisited.neighborMap.values()) {
                    if (distances[neighbor.id] == -1) {
                        bfsQueue.add(neighbor);
                        distances[neighbor.id] = distances[nodeVisited.id] + 1;
                    }
                }
            }

            Arrays.sort(distances);
//            TestHelper.printArray(distances);
            int maxDistance = distances[distances.length - 1];
            if (maxDistance == 0) return 0;
            int count = 0;
            for (int i = distances.length - 1; i >= 1; i--) {
                if (maxDistance == distances[i]) count++;
            }
            return count;
        }
    }
}