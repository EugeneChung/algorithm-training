package programmers.greedy;

import helpers.TestHelper;

public class Joystick {
    public static void main(String[] args) {
//        String name = "AAAB"; //2
//        String name = "AABA"; //3
//        String name = "ABABA"; //5
//        String name = "AAABAAA"; //4
//        String name = "AAAABAA"; //4
//        String name = "JAZ"; //11
//        String name = "JEROEN"; //56
//        String name = "A"; //0
//          String name = "AA"; //0
//        String name = "B"; //1
//          String name = "BA"; //1
//        String name = "AB"; //2
//        String name = "JAN"; //23
        String name = "AZAAAZ"; //5
        
        TestHelper.printSolution(
            new Solution().solution(name)
        );
    }
    static class Solution {
        private enum Direction {
            LEFT, RIGHT;
        }

        public int solution(String name) {
            if (name.length() == 1) return countCharacterChange(name, 0);

            Direction direction = Direction.RIGHT;
            int answer = 0;
            int current = 0;
            do {
                answer += countCharacterChange(name, current);
                if (name.charAt(goRight(name, current)) == 'A') {
                    direction = Direction.LEFT;
                }
                if (direction == Direction.RIGHT) {
                    current = goRight(name, current);
                } else {
                    current = goLeft(name, current);
                }
                answer++; // for direction move
                break;
            } while (true);

            return answer;
        }

        private int countCharacterChange(String name, int current) {
            char targetChar = name.charAt(current);
            if (targetChar != 'A') {
                int upward = targetChar - 'A';
                int downward = 'A' - targetChar + 26;
                return Math.min(upward, downward);
            }
            return 0;
        }

        private int goRight(String name, int current) {
            if (current == name.length() - 1) {
                return 0;
            } else {
                return current + 1;
            }
        }

        private int goLeft(String name, int current) {
            if (current == 0) {
                return name.length() - 1;
            } else {
                return current - 1;
            }
        }
    }
}