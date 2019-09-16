package programmers.hash;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class 위장 {
    public static void main(String[] args) {
        String[][] clothes =
            {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}} // 5
            ;

        Object solution = new Solution().solution(clothes);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(String[][] clothes) {
            int answer = 0;
            Map<String, Integer> clothesMap = new HashMap<>();
            for (String[] nameAndType : clothes) {
                int namesCount = clothesMap.getOrDefault(nameAndType[1], 0);
                clothesMap.put(nameAndType[1], ++namesCount);
            }

            if (clothesMap.size() == 1) {
                answer = clothes.length;
            } else {
                for (int count : clothesMap.values()) {
                    if (answer == 0) answer = count + 1;
                    else answer = answer * (count + 1);
                }
                answer -= 1;
            }

            return answer;
        }
    }
}