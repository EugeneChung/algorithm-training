package codility.lesson1;

public class BinaryGap {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2147483647));
    }

    static class Solution {
        private enum State {
            INITIAL,
            CONSECUTIVE_0,
            START_1
        }

        public int solution(int N) {
            State state = State.INITIAL;
            int temp = N;
            int binaryGap = 0;
            int binaryGapCandidate = 0;

            while (temp > 0) {
                if (temp % 2 == 0) {
                    switch (state) {
                        case START_1:
                            binaryGapCandidate = 1;
                            state = State.CONSECUTIVE_0;
                            break;
                        case CONSECUTIVE_0:
                            binaryGapCandidate++;
                    }
                } else {
                    switch (state) {
                        case INITIAL:
                            state = State.START_1;
                            break;
                        case CONSECUTIVE_0:
                            binaryGap = Math.max(binaryGap, binaryGapCandidate);
                            binaryGapCandidate = 0;
                            state = State.START_1;
                            break;
                    }
                }
                temp = temp >> 1;
            }
            return binaryGap;
        }

    }
}