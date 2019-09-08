package codility.lesson7;

import helpers.TestHelper;

import java.util.Stack;

public class Fish {
    public static void main(String[] args) {
        int[] A =
//            {4, 3, 2, 1, 5}
//            {4, 3, 2, 1, 5}
//            {4, 3, 2, 1, 5}
//            {4, 3, 2, 1, 5}
            {4, 3}
            ;
        int[] B =
//            {0, 1, 0, 0, 0}
//            {0, 1, 1, 0, 0}
//            {1, 1, 0, 0, 0}
//            {0, 0, 0, 0, 0}
            {1, 1}
            ;
        Object solution = new Solution().solution(A, B);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A, int[] B) {
            if (A.length == 1) return 1;

            int alive = 0;
            Stack<Integer> downstreamFishs = new Stack<>();
            for (int i = 0; i < B.length; i++) {
                if (B[i] == 1) {
                    downstreamFishs.push(i);
                } else {
                    while (!downstreamFishs.isEmpty()) {
                        int downFish = downstreamFishs.pop();
                        if (A[downFish] >= A[i]) { // (= never happen)
                            downstreamFishs.push(downFish);
                            break;
                        }
                    }
                    if (downstreamFishs.isEmpty()) alive++;
                }
            }
            return alive + downstreamFishs.size();
        }
    }
}