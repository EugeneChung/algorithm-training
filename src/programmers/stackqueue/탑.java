package programmers.stackqueue;

import helpers.TestHelper;

import java.util.ArrayDeque;
import java.util.Queue;

public class 탑 {
    public static void main(String[] args) {
//        int[] heights = {6,9,5,7,4}; //[0,0,2,2,4]
//        int[] heights = {3,9,9,3,5,7,2}; //0,0,0,3,3,3,6
//        int[] heights = {1,5,3,6,7,6,5}; //0,0,2,0,0,5,6
//        int[] heights = {1,2, 3, 4, 5}; //0,0,0,0,0
//        int[] heights = {1,2}; //0,0
//        int[] heights = {1,1}; //0,0
//        int[] heights = {2,1}; //0,1
        int[] heights = {2,1,3}; //0,1,0

        TestHelper.printSolution(new Solution().solution(heights));
    }

    static class Solution {
        public int[] solution(int[] heights) {
            int[] answer = new int[heights.length];
            for (int i = heights.length - 1; i >= 1; i--) {
                for (int j = i; j >= 0; j--) {
                    if (heights[i] < heights[j]) {
                        answer[i] = j + 1;
                        break;
                    }
                }
            }
            //answer[0] = 0;
            return answer;
        }
    }
}