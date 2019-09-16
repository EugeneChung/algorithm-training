package programmers.sorting;

import helpers.TestHelper;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        int[] citations = {1, 0}; //1
//        int[] citations = {0, 0}; //0
//        int[] citations = {0}; //0
//        int[] citations = {3, 0, 6, 1, 5}; //3, [0 1 3 5 6]
        TestHelper.printSolution(new Solution().solution(citations));
    }

    static class Solution {
        public int solution(int[] citations) {
            Arrays.sort(citations);
            for (int h = citations[citations.length - 1]; h > 0; h--) {
                int loc = Arrays.binarySearch(citations, h);
                if (loc < 0) {
                    loc = -(loc + 1);
                }
                int citatedCount = citations.length - loc;
                if (citatedCount >= h && loc <= h) {
                    return h;
                }
            }
            return 0;
        }
    }
}