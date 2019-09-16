package programmers.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthValue {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(new Solution().solution(array, commands)));
    }

    static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            List<Integer> answer = new ArrayList<>();
            for (int[] command : commands) {
                int[] targetArray = Arrays.copyOfRange(array, command[0] - 1, command[1]);
                Arrays.sort(targetArray);
                answer.add(targetArray[command[2] - 1]);
            }
            return answer.stream().mapToInt(x -> x).toArray();
        }
    }
}