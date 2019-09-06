package programmers;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainingCostume {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {1, 2, 3};
        int[] reserve = {2, 3};
        System.out.println(solution(n, lost, reserve));
    }


    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        Set<Integer> lostSet = IntStream.of(lost).boxed().collect(Collectors.toSet());
        Set<Integer> reserveSet = IntStream.of(reserve).boxed().collect(Collectors.toSet());

        Iterator<Integer> iterator = lostSet.iterator();
        while (iterator.hasNext()) {
            int lostStudent = iterator.next();
            if (reserveSet.remove(lostStudent)) {
                answer++;
                iterator.remove();
            }
        }

        for (int lostStudent : lostSet) {
            int _try = lostStudent - 1;
            if (reserveSet.remove(_try)) {
                answer++;
            } else {
                _try = lostStudent + 1;
                if (reserveSet.remove(_try)) {
                    answer++;
                }
            }
        }

        return answer;
    }
}