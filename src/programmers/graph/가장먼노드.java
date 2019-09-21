package programmers.graph;

import helpers.TestHelper;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class 가장먼노드 {
    public static void main(String[] args) {
//        int n = 6; int[][] edge = {{3, 6}, {4, 3}, {3,2},{1,3},{1,2},{2,4},{5,2}}; //3
//        int n = 4; int[][] edge = {{1,2},{2, 3},{3,4}}; //1
//        int n = 5; int[][] edge = {{1,2},{2, 3},{3,4},{3,5}}; //2
//        int n = 4; int[][] edge = {{1,2},{2,3},{3,4},{2,4}}; //2
//        int n = 8; int[][] edge = {{1,2},{2,3},{3,4},{2,4},{1,5},{3,5},{5, 6},{6, 7},{7,8},{1,8}}; //4
//        int n = 2; int[][] edge = {{1,1},{1,2}}; //1
        int n = 4; int[][] edge = {{1,2},{3,4}}; //1

        TestHelper.printSolution(new Solution().solution(n, edge));
    }

    static class Solution {
        class Node {
            final int id;
            final Map<Integer, Node> neighborMap = new HashMap<>();
            int distanceFromNodeOne = Integer.MAX_VALUE;
            int minDistanceFromNodeOneOfNeighbor = Integer.MAX_VALUE;

            public Node(int id) {
                this.id = id;
            }

            void addNeighbor(Node neighbor) {
                if (neighbor.id != id) {
                    neighborMap.put(neighbor.id, neighbor);
                }
            }

            public String toString() {
                return "V[" + id + ": distanceFromNodeOne=" + distanceFromNodeOne + "]";
            }
        }

        public int solution(int n, int[][] edge) {
            Node[] nodes = new Node[n + 1];
            for (int i = 0; i <= n; i++) {
                nodes[i] = new Node(i);
            }
            nodes[1].distanceFromNodeOne = 0;

            for (int[] e : edge) {
                nodes[e[0]].addNeighbor(nodes[e[1]]);
                nodes[e[1]].addNeighbor(nodes[e[0]]);
            }

            Queue<Node> bfsQueue = new ArrayDeque<>(nodes.length);
            boolean[] visited = new boolean[nodes.length];
            bfsQueue.add(nodes[1]);

            while (!bfsQueue.isEmpty()) {
                Node nodeVisited = bfsQueue.poll();
                visited[nodeVisited.id] = true;
                if (nodeVisited.id == 1) {
                    nodeVisited.distanceFromNodeOne = 0;
                } else if (nodeVisited.neighborMap.containsKey(1)) {
                    nodeVisited.distanceFromNodeOne = 1;
                } else {
                    int minDistance = Integer.MAX_VALUE;
                    for (Node neighbor : nodeVisited.neighborMap.values()) {
                        minDistance = Math.min(minDistance, neighbor.distanceFromNodeOne);
                    }
                    nodeVisited.distanceFromNodeOne = minDistance + 1;
                }
                for (Node neighbor : nodeVisited.neighborMap.values()) {
                    if (visited[neighbor.id]) {
                        nodes[neighbor.id].distanceFromNodeOne = Math.min(nodeVisited.distanceFromNodeOne + 1, nodes[neighbor.id].distanceFromNodeOne);
                    } else {
                        bfsQueue.add(neighbor);
                    }
                    //nodeVisited.minDistanceFromNodeOneOfNeighbor = Math.min(nodeVisited.minDistanceFromNodeOneOfNeighbor, neighbor.distanceFromNodeOne);
                }
            }

//            TestHelper.printArray(nodes);

            int maxDistanceFromNodeOne = 0;
            Map<Integer, Integer> counterMap = new HashMap<>();
            for (Node node : nodes) {
                if (node.distanceFromNodeOne == Integer.MAX_VALUE) continue;
                maxDistanceFromNodeOne = Math.max(maxDistanceFromNodeOne, node.distanceFromNodeOne);
                counterMap.compute(node.distanceFromNodeOne, (k, v) -> {
                    if (v == null) return 1; else return v + 1;
                });
            }
//            TestHelper.log(maxDistanceFromNodeOne);
//            TestHelper.log(counterMap);
            if (maxDistanceFromNodeOne == 0) return 0;
            return counterMap.get(maxDistanceFromNodeOne);
        }

        private void dfs(Node[] nodes, boolean[] visited, int visit, int distance) {
            visited[visit] = true;
            Node nodeVisited = nodes[visit];
            if (nodeVisited.neighborMap.containsKey(1)) {
                nodeVisited.distanceFromNodeOne = 1;
            } else {
                nodeVisited.distanceFromNodeOne = distance;
            }
            for (int neighbor : nodeVisited.neighborMap.keySet()) {
                if (visited[neighbor]) {
                    nodes[neighbor].distanceFromNodeOne = Math.min(nodeVisited.distanceFromNodeOne + 1, nodes[neighbor].distanceFromNodeOne); 
                } else {
                    dfs(nodes, visited, neighbor, nodeVisited.distanceFromNodeOne + 1);
                }
            }
        }
    }
}