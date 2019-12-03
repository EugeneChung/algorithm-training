package programmers.greedy;

import helpers.TestHelper;

public class 큰수만들기 {
    public static void main(String[] args) {
//        String number = "1924"; int k = 2; //94
//        String number = "1231234"; int k = 3; //3234
//        String number = "4177252841"; int k = 4; //775841
//        String number = "100000"; int k = 3; //100
        String number = "100010"; int k = 3; //110

        TestHelper.printSolution(
            new Solution().solution(number, k)
        );
    }

    static class Solution {
        public String solution(String number, int k) {
            if (number.charAt(0) == '0') return "0";
            int removed = 0;

            StringBuilder result = new StringBuilder();
            result.append(number.charAt(0));
            for (int i = 1; i < number.length(); i++) {
                char c = number.charAt(i);
                if (removed < k) {
                    for (int j = result.length() - 1; j >= 0; j--) {
                        if (result.charAt(j) < c) {
                            result.deleteCharAt(j);
                            removed++;
                            if (removed == k) break;
                        } else {
                            break;
                        }
                    }
                }
                result.append(c);
            }
            for (; removed < k; removed++) {
                result.deleteCharAt(result.length() - 1);
            }
            return result.toString();
        }
    }
}
