package programmers.completesearch;

import helpers.TestHelper;

public class 카펫 {
    public static void main(String[] args) {
//        int brown = 10, red = 2; // 4,3
//        int brown = 8, red = 1; //3,3
        int brown = 24, red = 24; //8,6
        TestHelper.printSolution(new Solution().solution(brown, red));
    }

    static class Solution {
        public int[] solution(int brown, int red) {
            for (int width = 3; width < 2500; width++) {
                for (int height = 3; height <= width; height++) {
                    int targetBrown = width * 2 + (height - 2) * 2;
                    if (targetBrown == brown) {
                        int targetRed = (width - 2) * (height - 2);
                        if (targetRed == red) {
                            return new int[] {width, height};
                        }
                    }
                }
            }
            return new int[]{0, 0};
        }
    }
}