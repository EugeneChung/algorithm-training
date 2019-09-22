package programmers.graph;

import helpers.TestHelper;

import java.util.*;

public class 순위 {
    public static void main(String[] args) {
        int n = 6; int[][] results = {{4, 3}, {4,2},{3,2},{1,2},{2,5}}; //2
//        int n = 4; int[][] results = {{1,2},{2, 3},{3,4}}; //1
//        int n = 5; int[][] results = {{1,2},{2, 3},{3,4},{3,5}}; //2
//        int n = 4; int[][] results = {{1,2},{2,3},{3,4},{2,4}}; //2
//        int n = 8; int[][] results = {{1,2},{2,3},{3,4},{2,4},{1,5},{3,5},{5, 6},{6, 7},{7,8},{1,8}}; //4
//        int n = 2; int[][] results = {{1,1},{1,2}}; //1
//        int n = 4; int[][] results = {{1,2},{3,4}}; //1

        TestHelper.printSolution(new Solution().solution(n, results));
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

            public String toString() {
                return "V[" + id + ": neighbors=" + neighborMap.keySet() + "]";
            }
        }

        public int solution(int n, int[][] results) {
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i + 1);
            }

            for (int[] e : results) {
                nodes[e[1] - 1].addNeighbor(nodes[e[0] - 1]);
            }

            TestHelper.printArray(nodes);

            return 0;
        }
    }
}