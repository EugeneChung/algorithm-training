package programmers.dfsbfs;

import java.util.*;

public class Network {
    public static void main(String[] args) {
        //int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int reserve = 3;
        System.out.println("Solution : " + new Solution().solution(reserve, computers));
    }

    static class Solution {
        public int solution(int n, int[][] computers) {
            Queue<Integer> networkRootCandidate = new LinkedList<>();
            Stack<Integer> nodes = new Stack<>();
            Map<Integer, Set<Integer>> networkMap = new HashMap<>();

            networkRootCandidate.add(0);

            while (!networkRootCandidate.isEmpty()) {
                int rootCandidate = networkRootCandidate.poll();

                Set<Integer> network = networkMap.get(rootCandidate);
                if (network == null) {
                    network = new HashSet<>(Collections.singletonList(rootCandidate));
                    networkMap.put(rootCandidate, network);
                } else {
                    continue;
                }

                nodes.push(rootCandidate);
                while (!nodes.empty()) {
                    int i = nodes.pop();

                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            if (computers[i][j] == 1) {
                                if (network.add(j)) {
                                    networkMap.put(j, network);
                                    networkRootCandidate.remove(j);
                                    nodes.push(j);
                                }
                            } else if (!networkMap.containsKey(j)) {
                                networkRootCandidate.add(j);
                            }
                        }
                    }
                }
            }
            return new HashSet<>(networkMap.values()).size();
        }
    }
}