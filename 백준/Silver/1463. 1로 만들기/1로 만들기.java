import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        for (int i = N; i > 0; i--) {
            dp[i / 3] = i % 3 == 0 ? Math.min(dp[i / 3], dp[i] + 1) : dp[i / 3];
            dp[i / 2] = i % 2 == 0 ? Math.min(dp[i / 2], dp[i] + 1) : dp[i / 2];
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }
        System.out.println(dp[1]);
    }
}