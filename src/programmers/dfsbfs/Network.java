package programmers.dfsbfs;

import helpers.TestHelper;

public class Network {
    public static void main(String[] args) {
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}; int n = 3; // 2
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}; int n = 3; //1
        TestHelper.printSolution(new Solution().solution(n, computers));
    }

    static class Solution {
        public int solution(int n, int[][] computers) {
            int answer = 0;
            boolean[] linked = new boolean[computers.length];
            for (int i = 0; i < computers.length; i++) {
                if (!linked[i]) {
                    findLinkedComputers(computers, linked, i);
                    answer++;
                }
            }
            return answer;
        }

        private void findLinkedComputers(int[][] computers, boolean[] linked, int targetComputer) {
            linked[targetComputer] = true; // important!
            for (int i = 0; i < computers.length; i++) {
                if (targetComputer != i && computers[targetComputer][i] == 1 && !linked[i]) {
                    findLinkedComputers(computers, linked, i);
                }
            }
        }
    }
}