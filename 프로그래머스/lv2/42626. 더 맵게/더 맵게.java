import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int number : scoville) {
            queue.offer(number);
        }

        while (!queue.isEmpty()) {
            if (queue.peek() >= K) {
                break;
            }
            
            if (queue.size() == 1) {
                answer = -1;
                break;
            }

            queue.offer(queue.poll() + (queue.poll() * 2));
            answer++;
        }
        
        return answer;
    }
}