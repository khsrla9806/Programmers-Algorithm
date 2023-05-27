import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp = new int[str2.length + 1][str1.length + 1];
        int answer = 0;
        for (int col = 1; col <= str1.length; col++) {
            char value = str1[col - 1];
            for (int row = 1; row <= str2.length; row++) {
                // 위에서 가져온 값
                int valueOfTop = dp[row][col - 1];
                // 왼쪽에서 가져온 값
                int valueOfLeft = dp[row - 1][col];

                // 둘 중에 큰 값으로 먼저 갱신
                dp[row][col] = Math.max(valueOfTop, valueOfLeft);

                // 문자가 같은 경우 공통 문자가 추가되었기 때문에 [row - 1][col - 1]에 +1을 해준다.
                if (str1[col - 1] == str2[row - 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row - 1][col - 1] + 1);
                }
                answer = Math.max(answer, dp[row][col]);
            }
        }
        System.out.println(answer);
    }
}
