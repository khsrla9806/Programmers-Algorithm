import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // step 1. 입력 값 정리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int[] students = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // step 2. 학생들 사이의 키 차이를 구해서 저장 (오른쪽 학생이 항상 왼쪽 학생보다 큼)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            queue.offer(students[i] - students[i - 1]);
        }

        // step 3. diff를 정렬하여 차이가 큰 것부터 k-1개를 제거
        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }
        System.out.println(answer);
    }
}
