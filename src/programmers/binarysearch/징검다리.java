package programmers.binarysearch;

import helpers.TestHelper;

import java.util.Arrays;

public class 징검다리 {
    public static void main(String[] args) {
        int distance = 25; int[] rocks = {2, 14, 11, 21, 17}; int n = 2; //4

        TestHelper.printSolution(new Solution().solution(distance, rocks, n));
    }

    static class Solution {
        public int solution(int distance, int[] rocks, int n) {
            Arrays.sort(rocks);

            int low = 1;
            int high = distance;
            int answer = 0;

            while (low <= high) {
                int mid = (low + high) / 2;
                int prevRock = 0;
                int removedRocks = 0;
                for (int rock : rocks) {
                    int gap = rock - prevRock;
                    if (gap < mid) removedRocks++;
                    else prevRock = rock;
                }

                if (removedRocks > n) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                    answer = mid;
                }
            }

            return answer;
        }
    }
}