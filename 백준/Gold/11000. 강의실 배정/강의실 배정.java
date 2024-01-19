import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // step 1. 입력 값을 정리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] lectures = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            lectures[i][0] = Integer.parseInt(tokenizer.nextToken());
            lectures[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // step 2. 강의 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(lectures, (l1, l2) -> l1[0] - l2[0]);

        // step 3. 정답 도출
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] lecture : lectures) {
            if (!endTimes.isEmpty() && endTimes.peek() <= lecture[0]) {
                endTimes.poll();
            }
            endTimes.offer(lecture[1]);
        }
        System.out.println(endTimes.size());
    }
}
