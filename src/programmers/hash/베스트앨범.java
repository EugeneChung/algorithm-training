package programmers.hash;

import helpers.TestHelper;

import java.util.*;
import java.util.stream.Collectors;

public class 베스트앨범 {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"}; int[] plays = {500, 600, 150, 800, 2500}; //{4, 1, 3, 0} 

        Object solution = new Solution().solution(genres, plays);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private static class PlayInfo implements Comparable<PlayInfo> {
            int playCount;
            int id;

            PlayInfo(int playCount, int id) {
                this.playCount = playCount;
                this.id = id;
            }

            @Override
            public int compareTo(PlayInfo o) {
                if (o.playCount < this.playCount) return 1;
                else if (o.playCount > this.playCount) return -1;
                else {
                    return Integer.compare(o.id, this.id);
                }
            }
        }

        private static class GenreInfo implements Comparable<GenreInfo> {
            long sum;
            TreeSet<PlayInfo> playsSet = new TreeSet<>();

            @Override
            public int compareTo(GenreInfo o) {
                if (o.sum > this.sum) return 1;
                else if (o.sum == this.sum) return 0;
                return -1;
            }

            void merge(int playCount, int id) {
                this.sum += playCount;
                playsSet.add(new PlayInfo(playCount, id));
            }
        }

        public int[] solution(String[] genres, final int[] plays) {
            Map<String, GenreInfo> topGenres = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                int play = plays[i];
                GenreInfo genreInfo = topGenres.get(genres[i]);
                if (genreInfo == null) {
                    GenreInfo newInfo = new GenreInfo();
                    newInfo.sum = play;
                    newInfo.playsSet.add(new PlayInfo(play, i));
                    topGenres.put(genres[i], newInfo);
                } else {
                    genreInfo.merge(play, i);
                }
            }

            LinkedHashMap<String, GenreInfo> sortedMap =
                topGenres.entrySet().stream().
                    sorted(Map.Entry.comparingByValue()).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

            List<Integer> answerList = new ArrayList<>();
            for (Map.Entry<String, GenreInfo> entry : sortedMap.entrySet()) {
                TreeSet<PlayInfo> playsSet = entry.getValue().playsSet;

                PlayInfo playInfo = playsSet.pollLast();
                assert playInfo != null;
                answerList.add(playInfo.id);

                playInfo = playsSet.pollLast();
                if (playInfo != null) {
                    answerList.add(playInfo.id);
                }
            }
            return answerList.stream().mapToInt(i -> i).toArray();
        }
    }
}