import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int numberCount = (N / 2) + 1;
        int[][][] dp = new int[numberCount + 1][numberCount + 1][2]; // 0은 사용하지 않음
        String[] operation = new String[numberCount + 1];

        String[] inputLine = reader.readLine().split("");
        for (int i = 1; i <= N; i++) {
            if (i % 2 == 0) { // 짝수는 연산자
                operation[(i / 2) + 1] = inputLine[i - 1];
                continue;
            }
            dp[(i / 2) + 1][(i / 2) + 1][0] = Integer.parseInt(inputLine[i - 1]);
            dp[(i / 2) + 1][(i / 2) + 1][1] = Integer.parseInt(inputLine[i - 1]);
        }

        for (int y = 2; y <= numberCount; y++) {
            for (int x = y - 1; x > 0; x--) {
                int cors = x;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                while (cors < y) {
                    int[] cors1 = {x, cors++};
                    int[] cors2 = {cors, y};
                    int[] result = calculateMaxAndMin(dp[cors1[0]][cors1[1]], dp[cors2[0]][cors2[1]], operation[cors]);
                    max = Math.max(max, result[0]);
                    min = Math.min(min, result[1]);
                }
                dp[x][y][0] = max;
                dp[x][y][1] = min;
            }
        }

        System.out.println(dp[1][numberCount][0]);
    }

    private static int[] calculateMaxAndMin(int[] spot1, int[] spot2, String operation) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        if (operation.equals("*")) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    max = Math.max(max, spot1[i] * spot2[j]);
                    min = Math.min(min, spot1[i] * spot2[j]);
                }
            }
        } else if (operation.equals("+")) {
            max = spot1[0] + spot2[0];
            min = spot1[1] + spot2[1];
        } else if (operation.equals("-")) {
            max = spot1[0] - spot2[1];
            min = spot1[1] - spot2[0];
        }
        return new int[]{max, min};
    }
}
