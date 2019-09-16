package programmers.hash;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {
    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"}, completion = {"eden", "kiki"}; // leo

        Object solution = new Solution().solution(participant, completion);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            Map<String, Integer> completionMap = new HashMap<String, Integer>();

            for (String c : completion) {
                int count = completionMap.getOrDefault(c, 0);
                completionMap.put(c, ++count);
            }

            for (String p : participant) {
                int count = completionMap.getOrDefault(p, 0);
                if (count == 0) {
                    answer = p;
                    break;
                }
                completionMap.put(p, --count);
            }

            return answer;
        }
    }
}