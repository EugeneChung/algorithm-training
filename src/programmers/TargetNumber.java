package programmers;

public class TargetNumber {
    public static void main(String[] args) {
        int[] lost = {1, 1, 1, 1, 1};
        int reserve = 3;
        System.out.println(new Solution().solution(lost, reserve));
    }

    static class Solution {
        private int traverseNumbers(final int[] numbers, final int target, int index, int sum) {
            if (index == numbers.length) {
                if (sum == target) {
                    return 1;
                }
                return 0;
            } else {
                int number = numbers[index];
                return traverseNumbers(numbers, target, index + 1, sum + number) +
                    traverseNumbers(numbers, target, index + 1, sum - number);
            }
        }

        public int solution(int[] numbers, int target) {
            return traverseNumbers(numbers, target, 0, 0);
        }
    }
}