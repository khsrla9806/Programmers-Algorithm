import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            int[] dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                int coin = Integer.parseInt(st.nextToken());
                coins[i] = coin;
            }
            for (int c = 0; c < N; c++) {
                int coin = coins[c];
                for (int i = 1; i <= M; i++) {
                    if (i - coin >= 0) {
                        dp[i] += dp[i - coin];
                    }
                }
            }
            System.out.println(dp[M]);
        }
    }
}