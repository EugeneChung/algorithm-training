package codility.lesson15;

import helpers.TestHelper;

import java.util.Arrays;

public class CountTriangle {
    public static void main(String[] args) {
        int[] A =
            {10, 2, 5, 1, 8, 12, 11, 15} // 12
//            {10, 2, 5, 1, 8, 12} // 4
//            {3, 2, 1} // 0
//            {3, 2, 7} // 0
//            {3, 2, 3} // 1
//            {} // 0
//            {1} // 0
//            {1, 2} // 0
//            {1, 2} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 3) return 0;

            Arrays.sort(A);

            int left = 0;
            int right = 2;
            int answer = 0;

            while (right < A.length) {
                if (right - left >= 2 && A[left] + A[left + 1] > A[right]) {
                    answer++;
                }
//                TestHelper.log("left=" + left + ", right=" + right + ", answer=" + answer);

                if (right < A.length - 1) {
                    // check to move right
                    if (right == left) {
                        if (right < A.length - 2) {
                            if (A[right] + A[right + 1] > A[right + 2]) {
                                right += 2;
                            } else {
                                answer += findTriangeWhileMovingLeft(A, left, right);
                                left++;
                            }
                        } else {
                            break;
                        }
                    } else if (right - left == 1) {
                        if (A[left] + A[right] > A[right + 1]) {
                            right++;
                        } else {
                            answer += findTriangeWhileMovingLeft(A, left, right);
                            left++;
                        }
                    } else if (A[left] + A[left + 1] > A[right + 1]) {
                        right++;
                    } else {
                        answer += findTriangeWhileMovingLeft(A, left, right);
                        left++;
                    }
                } else {
                    answer += findTriangeWhileMovingLeft(A, left, right);
                    left++;
                    if (right - left < 2) {
                        break;
                    }
                }
            }

            return answer;
        }

        private int findTriangeWhileMovingLeft(int[] A, int left, int right) {
            int count = 0;
            if (right - left >= 3) {
                int i = 2;
                while (left + i < right) {
                    if (A[left] + A[left + i] > A[right]) {
                        count++;
                    }
                    i++;
                }
            }
            return count;
        }
    }
}