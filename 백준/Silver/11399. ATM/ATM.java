import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine(); // 사용하지 않을 n

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while (tokenizer.hasMoreTokens()) {
            queue.offer(Integer.parseInt(tokenizer.nextToken()));
        }
        int answer = 0;
        int time = 0;
        while (!queue.isEmpty()) {
            time += queue.poll();
            answer += time;
        }
        System.out.println(answer);
    }
}