package programmers.hash;

import helpers.TestHelper;

import java.util.*;

public class 베스트앨범 {
    public static void main(String[] args) {
//        String[] genres = {"classic", "pop", "classic", "classic", "pop"}; int[] plays = {500, 600, 150, 800, 2500}; //{4, 1, 3, 0} 
        String[] genres = {"classic", "pop", "classic", "classic", "kpop"}; int[] plays = {500, 600, 500, 800, 2500}; //{4, 3, 0, 1} 

        Object solution = new Solution().solution(genres, plays);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        class SongInfo implements Comparable<SongInfo> {
            final int id;
            final int playCount;

            SongInfo(int id, int playCount) {
                this.id = id;
                this.playCount = playCount;
            }

            @Override
            public int compareTo(SongInfo o) {
                int compare = Integer.compare(this.playCount, o.playCount);
                if (compare == 0) {
                    compare = Integer.compare(o.id, this.id);
                }
                return compare;
            }
        }

        private class GenreInfo implements Comparable<GenreInfo> {
            final TreeSet<SongInfo> sortedSongs = new TreeSet<>();
            int totalPlayCount;

            @Override
            public int compareTo(GenreInfo o) {
                return Integer.compare(totalPlayCount, o.totalPlayCount);
            }
        }

        public int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();
            Map<String, GenreInfo> genreInfoMap = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                String genre = genres[i];
                int play = plays[i];
                GenreInfo genreInfo = genreInfoMap.getOrDefault(genre, new GenreInfo());
                if (genreInfo.totalPlayCount == 0) {
                    genreInfoMap.put(genre, genreInfo);
                }
                genreInfo.totalPlayCount += play;
                genreInfo.sortedSongs.add(new SongInfo(i, play));
            }

            TreeSet<GenreInfo> sortedGenresByTotalPlayCount = new TreeSet<>(genreInfoMap.values());
            while (!sortedGenresByTotalPlayCount.isEmpty()) {
                GenreInfo genreInfo = sortedGenresByTotalPlayCount.pollLast();
                SongInfo songInfo = genreInfo.sortedSongs.pollLast();
                answer.add(songInfo.id);
                songInfo = genreInfo.sortedSongs.pollLast();
                if (songInfo != null) {
                    answer.add(songInfo.id);
                }
            }

            return answer.stream().mapToInt(x -> x).toArray();
        }
    }
}