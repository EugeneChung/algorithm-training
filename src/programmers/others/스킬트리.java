package programmers.others;

import helpers.TestHelper;

import java.util.HashSet;
import java.util.Set;

public class 스킬트리 {
    public static void main(String[] args) {
//        String skill = "CBD"; String[] skillTrees = {"BACDE","CBADF","AECB","BDA"}; //2
//        String skill = "CBD"; String[] skillTrees = {"BACDE"}; //0
        String skill = "CBD"; String[] skillTrees = {"XYZ"}; //1

        Object solution = new Solution().solution(skill, skillTrees);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;
            Set<Character> skillSet = new HashSet<>();
            for (int i = 0; i < skill.length(); i++) {
                skillSet.add(skill.charAt(i));
            }
            for (String skillTree : skill_trees) {
                int next = 0;
                for (int i = 0; i < skillTree.length(); i++) {
                    char c = skillTree.charAt(i);
                    if (skillSet.contains(c)) {
                        if (c == skill.charAt(next)) {
                            next++;
                        } else {
                            next = -1;
                            break;
                        }
                    }
                }
                if (next >= 0) {
                    answer++;
                }
            }
            return answer;
        }
    }
}
