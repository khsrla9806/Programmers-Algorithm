import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i] = new Item(W, V);
        }

        for (int weight = 1; weight <= K; weight++) {
            for (int i = 1; i <= N; i++) {
                dp[i][weight] = dp[i - 1][weight];
                if (items[i].W <= weight) {
                    dp[i][weight] = Math.max(dp[i][weight], items[i].V + dp[i - 1][weight - items[i].W]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}

class Item {
    int W, V;

    public Item(int W, int V) {
        this.W = W;
        this.V = V;
    }
}