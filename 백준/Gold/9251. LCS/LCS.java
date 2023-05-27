import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp = new int[str2.length + 1][str1.length + 1];
        for (int col = 1; col <= str1.length; col++) {
            for (int row = 1; row <= str2.length; row++) {
                // 문자가 같은 경우 공통 문자가 추가되었기 때문에 [row - 1][col - 1]에 +1을 해준다.
                if (str1[col - 1] == str2[row - 1]) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                }
                dp[row][col] = Math.max(dp[row][col], Math.max(dp[row][col - 1], dp[row - 1][col]));
            }
        }
        System.out.println(dp[str2.length][str1.length]);
    }
}
