package programmers.heap;

import helpers.TestHelper;

import java.util.PriorityQueue;

public class 더맵게 {
    public static void main(String[] args) {
//        int[] priorities = {1, 2, 3, 9, 10, 12}; int K = 7; // 2
//        int[] priorities = {1, 2, 3, 9, 10, 12}; int K = 10000; // -1
//        int[] priorities = {1, 2}; int K = 10000; // -1
//        int[] priorities = {1, 2}; int K = 3; // 1
        int[] priorities = {1, 2}; int K = 5; // 1

        TestHelper.printSolution(new Solution().solution(priorities, K));
    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            if (K == 0) return 0;
            if (scoville.length == 1) {
                if (scoville[0] >= K) return 0;
                else return -1;
            }

            int answer = 0;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(scoville.length);
            for (int v : scoville) priorityQueue.add(v);

            while (priorityQueue.size() > 1) {
                int minScoville = priorityQueue.poll();
                if (minScoville >= K) {
                    return answer;
                }
                int secondMinScoville = priorityQueue.poll();
                int newScoville = minScoville + 2 * secondMinScoville;
                priorityQueue.add(newScoville);
                answer++;

//                TestHelper.log("minScoville=" + minScoville + ", 2ndMinScoville=" + secondMinScoville + ", newScoville=" + newScoville);
            }

            int last = priorityQueue.poll();
            if (last >= K) {
                return answer;
            }
            return -1;
        }
    }
}