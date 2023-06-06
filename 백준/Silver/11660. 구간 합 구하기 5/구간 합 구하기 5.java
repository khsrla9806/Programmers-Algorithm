import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] sumMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumMap = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            // 가로의 합을 sumMap 갱신
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                sumMap[row][col] = sumMap[row][col - 1] + Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            int result = 0;
            for (int row = x1; row <= x2; row++) {
                result += sumMap[row][y2] - sumMap[row][y1 - 1];
            }
            builder.append(result).append("\n");
        }
        System.out.println(builder);
    }
}