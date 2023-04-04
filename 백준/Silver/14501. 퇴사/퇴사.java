import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] input = reader.readLine().split(" ");
            t[i] = Integer.parseInt(input[0]);
            p[i] = Integer.parseInt(input[1]);
        }

        int[] works = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            // 오늘 일을 할 수 있다면?
            if (i + t[i] <= n + 1) {
                works[i + t[i]] = Math.max(works[i] + p[i], works[i + t[i]]);
            }

            // 오늘 일을 할 수 없다면?
            works[i + 1] = Math.max(works[i + 1], works[i]);
        }
        System.out.println(works[n + 1]);
    }
}