package programmers.completesearch;

import helpers.TestHelper;

import java.util.HashSet;
import java.util.Set;

public class 숫자야구 {
    public static void main(String[] args) {
        int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}}; //2
        TestHelper.printSolution(new Solution().solution(baseball));
    }

    static class Solution {
        static void permutation(int[] arr, int depth, int n, int r, Set<Integer> targetNumbers) {
            if (depth == r) {
                targetNumbers.add(100 * arr[0] + 10 * arr[1] + arr[2]);
                return;
            }

            for (int i = depth; i < n; i++) {
                swap(arr, depth, i);
                permutation(arr, depth + 1, n, r, targetNumbers);
                swap(arr, depth, i);
            }
        }

        static void swap(int[] arr, int depth, int i) {
            int temp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = temp;
        }

        private static final int[] oneToNine = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        public int solution(int[][] baseball) {
            Set<Integer> targetNumbers = new HashSet<>();
            permutation(oneToNine, 0, oneToNine.length, 3, targetNumbers);
            //TestHelper.log(targetNumbers);

            int answer = 0;
            for (int targetNumber : targetNumbers) {
                boolean isPossible = true;
                for (int[] question : baseball) {
                    int strike = 0;
                    int ball = 0;

                    int firstFromTarget = targetNumber / 100;
                    int secondFromTarget = (targetNumber % 100) / 10;
                    int thirdFromTarget = (targetNumber % 100) % 10;
                    int firstFromQuestion = question[0] / 100;
                    int secondFromQuestion = (question[0] % 100) / 10;
                    int thirdFromQuestion = (question[0] % 100) % 10;
                    if (firstFromQuestion == firstFromTarget) {
                        strike++;
                    } else if (firstFromQuestion == secondFromTarget || firstFromQuestion == thirdFromTarget) {
                        ball++;
                    }
                    if (secondFromQuestion == secondFromTarget) {
                        strike++;
                    } else if (secondFromQuestion == firstFromTarget || secondFromQuestion == thirdFromTarget) {
                        ball++;
                    }
                    if (thirdFromQuestion == thirdFromTarget) {
                        strike++;
                    } else if (thirdFromQuestion == firstFromTarget || thirdFromQuestion == secondFromTarget) {
                        ball++;
                    }
                    if (question[1] != strike) {
                        isPossible = false;
                        break;
                    } else if (question[2] != ball) {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    answer++;
                }
            }
            return answer;
        }
    }
}