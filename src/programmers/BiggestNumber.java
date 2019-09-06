package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class BiggestNumber {
    public static void main(String[] args) {
        //int[] array = {10, 6, 2};
        int[] array = {0, 1, 0};
        System.out.println(new Solution().solution(array));
    }

    static class Solution {
        private static class StringCombitionComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        }

        public String solution(int[] numbers) {
            List<String> numStrings = Arrays.stream(numbers).mapToObj(String::valueOf).
                sorted(new StringCombitionComparator()).collect(Collectors.toList());
            //log(numStrings.toString());
            if (numStrings.get(numStrings.size() - 1).equals("0")) {
                return "0";
            }
            StringBuilder builder = new StringBuilder();
            for (int i = numStrings.size() - 1; i >= 0; i--) {
                builder.append(numStrings.get(i));
            }
            return builder.toString();
        }
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}