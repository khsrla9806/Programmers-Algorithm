import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineInput = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLineInput[0]);
        int k = Integer.parseInt(firstLineInput[1]);
        int[] coins = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            coins[i] = Integer.parseInt(reader.readLine());
        }

        int coinCnt = 0;
        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            if (k / coin != 0) {
                coinCnt += k / coin;
                k %= coin;
            }
        }
        System.out.println(coinCnt);
    }
}