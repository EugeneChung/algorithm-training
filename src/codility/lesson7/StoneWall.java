package codility.lesson7;

import helpers.TestHelper;

import java.util.Stack;

public class StoneWall {
    public static void main(String[] args) {
        int[] H =
            {8, 8, 5, 7, 9, 8, 7, 4, 8}
            ;
        Object solution = new Solution().solution(H);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] H) {
            int blocks = 0;
            Stack<Integer> stack = new Stack<>();

            stack.push(H[0]);
            blocks++;

            for (int i = 1; i < H.length; i++) {
                while (!stack.isEmpty() && stack.peek() > H[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(H[i]);
                    blocks++;
                } else {
                    if (stack.peek() < H[i]) {
                        stack.push(H[i]);
                        blocks++;
                    }
                }
            }

            return blocks;
        }
    }
}