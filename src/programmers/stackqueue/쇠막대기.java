package programmers.stackqueue;

import helpers.TestHelper;

import java.util.Stack;

public class 쇠막대기 {
    public static void main(String[] args) {
        String S = "()(((()())(())()))(())"; //17
//        String S = "()"; //0
//        String S = "()()"; //0
//        String S = "(())"; //2
//        String S = "(()())"; //3
//        String S = "((()))"; //4
//        String S = "((()()))"; //6

        TestHelper.printSolution(new Solution().solution(S));
    }

    static class Solution {
        public int solution(String arrangement) {
            if (arrangement.length() == 0) return 0;
            int[] laserCountPrefixSum = new int[arrangement.length() + 1];

            Stack<Integer> stack = new Stack<>();
            int answer = 0;

            for (int i = 0; i < arrangement.length(); i++) {
                if (arrangement.charAt(i) == '(') {
                    laserCountPrefixSum[i + 1] = laserCountPrefixSum[i];
                    stack.push(i);
                } else {
                    int pos = stack.pop();
                    if (pos == i - 1) {
                        laserCountPrefixSum[i + 1] = laserCountPrefixSum[i] + 1;
                    } else {
                        laserCountPrefixSum[i + 1] = laserCountPrefixSum[i];
                        answer += laserCountPrefixSum[i + 1] - laserCountPrefixSum[pos] + 1;
                    }
                }
            }
            return answer;
        }
    }
}