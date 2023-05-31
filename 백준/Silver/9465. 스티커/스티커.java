import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer top = new StringTokenizer(br.readLine());
            StringTokenizer bottom = new StringTokenizer(br.readLine());
            int[][] scores = new int[2][n + 1];
            for (int i = 1; i <= n; i++) {
                scores[0][i] = Integer.parseInt(top.nextToken());
                scores[1][i] = Integer.parseInt(bottom.nextToken());
            }

            int[][] dp = new int[2][n + 1];
            dp[0][1] = scores[0][1];
            dp[1][1] = scores[1][1];
            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + scores[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + scores[1][i];
            }
            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }
        System.out.println(sb);
    }
}
