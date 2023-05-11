import java.io.*;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] stepScores, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
        } else if (n == 2) {
            System.out.println(Integer.parseInt(br.readLine()) + Integer.parseInt(br.readLine()));
        } else {
            stepScores = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                stepScores[i] = Integer.parseInt(br.readLine());
            }

            dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = stepScores[0];
            dp[1] = stepScores[1];
            dp[2] = stepScores[1] + stepScores[2];

            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3] + stepScores[i - 1]) + stepScores[i];
            }
            System.out.println(dp[n]);
        }
    }
}