import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int lastDate = 0;
        LinkedList<Integer>[] info = new LinkedList[1001];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int date = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            if (info[date] == null) {
                info[date] = new LinkedList<>();
            }
            info[date].add(score);
            lastDate = Math.max(lastDate, date);
        }

        int answer = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int date = lastDate; date > 0; date--) {
            if (info[date] != null) {
                while (!info[date].isEmpty()) {
                    maxHeap.add(info[date].poll());
                }
            }
            if (!maxHeap.isEmpty()) {
                answer += maxHeap.poll();
            }
        }
        System.out.println(answer);
    }
}
