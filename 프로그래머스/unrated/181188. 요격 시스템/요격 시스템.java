import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (t1, t2) -> t1[1] - t2[1]);
        
        int answer = 0;
        int xCors = 0;
        for (int[] target : targets) {
            if (target[0] >= xCors) {
                xCors = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}