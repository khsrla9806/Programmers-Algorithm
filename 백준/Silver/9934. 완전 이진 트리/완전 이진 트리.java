import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(reader.readLine());
        int size = (int)Math.pow(2, k) - 1;
        int[] tree = new int[size];
        String[] inputData = reader.readLine().split(" ");
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.parseInt(inputData[i]);
        }

        Queue<Integer> index = new LinkedList<>();
        StringBuilder builder;
        index.offer(size / 2);
        int repeatCnt = 1;
        while (!index.isEmpty() && k != 0) {
            builder = new StringBuilder();
            int gapValue = (int)Math.pow(2, k - 2);
            for (int i = 0; i < repeatCnt; i++) {
                int temp = index.poll();
                builder.append(tree[temp]).append(" ");
                index.offer(temp - gapValue);
                index.offer(temp + gapValue);
            }
            k--;
            repeatCnt *= 2;
            System.out.println(builder);
        }
    }
}