import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] map = new int[n + 1];
        Arrays.fill(map, Integer.MAX_VALUE);
        map[map.length - 1] = 0;

        for (int i = map.length - 1; i > 0; i--) {
            int cnt = map[i] + 1;

            if (i % 3 == 0) map[i / 3] = Math.min(map[i / 3], cnt);
            if (i % 2 == 0) map[i / 2] = Math.min(map[i / 2], cnt);
            map[i - 1] = Math.min(map[i - 1], cnt);
        }

        System.out.println(map[1]);
    }
}