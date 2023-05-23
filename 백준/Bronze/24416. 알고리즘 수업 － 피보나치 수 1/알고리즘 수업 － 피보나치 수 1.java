import java.io.*;

public class Main {
    static int N, answer1, answer2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 재귀
        fibo(N);

        // DP
        fibonacci(N);

        // 결과
        System.out.println(answer1 + " " + answer2);
    }

    public static int fibo(int n) {
        if (n == 1 || n == 2) {
            answer1++;
            return 1;
        }
        return fibo(n - 2) + fibo(n - 1);
    }

    public static int fibonacci(int n) {
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            answer2++;
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}