import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        long n = Integer.parseInt(tokenizer.nextToken());
        long m = Integer.parseInt(tokenizer.nextToken());
        long min = 0;
        long max = Long.MIN_VALUE;

        List<Integer> list = new ArrayList<>();
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        while (tokenizer.hasMoreTokens()) {
            int height = Integer.parseInt(tokenizer.nextToken());
            list.add(height);
            max = Math.max(max, height);
        }

        PriorityQueue<Long> queue = new PriorityQueue<>(Collections.reverseOrder());
        // TODO: 이분탐색 시작
        long mid = 0;
        while (min <= max) {
            mid = (max + min) / 2;
            long slice = 0;
            for (int height : list) {
                if (height > mid) {
                    slice += height - mid;
                }
            }

            if (slice >= m) {
                min = mid + 1;
                queue.offer(mid);
            } else {
                max = mid - 1;
            }
        }
        System.out.println(queue.peek());
    }
}