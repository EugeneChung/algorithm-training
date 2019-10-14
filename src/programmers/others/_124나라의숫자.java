package programmers.others;

import helpers.TestHelper;

public class _124나라의숫자 {
    public static void main(String[] args) {
//        int n = 1;//1
//        int n = 3;//4
//        int n = 6;//14
        int n = 13;//111

        Object solution = new Solution().solution(n);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public String solution(int n) {
            int value = n;
            StringBuilder builder = new StringBuilder();
            while (value > 0) {
                char convert = convert(value);
                builder.insert(0, convert);
                value = value / 3;
                if (convert == '4') value--;
            }
            return builder.toString();
        }
        private char convert(int next) {
            switch (next % 3) {
                case 0: return '4';
                case 1: return '1';
                case 2: return '2';
                default: return '0'; // NEVER HAPPEN
            }
        }
    }
}
