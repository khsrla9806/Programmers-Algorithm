import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());
       
        PriorityQueue<Integer> leftOfCenter = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightOfCenter = new PriorityQueue<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(reader.readLine());
            if (leftOfCenter.size() == 0) {
                leftOfCenter.offer(input);
            } else if (leftOfCenter.size() != rightOfCenter.size()) {
                if (input < leftOfCenter.peek()) {
                    rightOfCenter.offer(leftOfCenter.poll());
                    leftOfCenter.offer(input);
                } else {
                    rightOfCenter.offer(input);
                }
            } else {
                if (input > rightOfCenter.peek()) {
                    leftOfCenter.offer(rightOfCenter.poll());
                    rightOfCenter.offer(input);
                } else {
                    leftOfCenter.offer(input);
                }
            }
            builder.append(leftOfCenter.peek()).append("\n");
        }
        System.out.println(builder);
    }
}