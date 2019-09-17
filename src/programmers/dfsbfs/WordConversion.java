package programmers.dfsbfs;

import helpers.TestHelper;

import java.util.*;
import java.util.stream.Collectors;

public class WordConversion {
    public static void main(String[] args) {
        String begin = "hot", target = "cog"; String[] words = {"hot", "dot", "dog", "lot", "log", "cog"}; //4
        TestHelper.printSolution(new Solution().solution(begin, target, words));
    }

    static class Solution {
        public int solution(String begin, String target, String[] words) {
            Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());
            if (!wordSet.contains(target)) {
                return 0;
            }

            List<Set<String>> conversionSets = new ArrayList<>();
            Set<String> conversionSet = new LinkedHashSet<>(Collections.singletonList(begin));
            convertString(wordSet, target, begin, conversionSet, conversionSets);
            int answer = Integer.MAX_VALUE;
            for (Set<String> set : conversionSets) {
                answer = Math.min(answer, set.size() - 1);
            }
            return answer;
        }

        private void convertString(final Set<String> wordSet, final String target, final String current,
                                   Set<String> conversionSet, List<Set<String>> conversionSets) {
            if (current.equals(target)) {
                conversionSets.add(new LinkedHashSet<>(conversionSet));
                return;
            }
            Set<String> localConversionSet = new LinkedHashSet<>(conversionSet);

            for (String str : wordSet) {
                if (str.equals(current) || localConversionSet.contains(str)) continue;
                if (isOneCharacterDifferent(current, str)) {
                    localConversionSet.add(str);
                    convertString(wordSet, target, str, localConversionSet, conversionSets);
                }
            }
        }

        private boolean isOneCharacterDifferent(String s1, String s2) {
            int difference = 0;
            for (int i = 0; i < s1.length(); ++i) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    difference++;
                }
            }
            return difference == 1;
        }
    }
}