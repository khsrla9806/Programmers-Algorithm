import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, sumMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        sumMap = new int[N + 1][N + 1];
        // map 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int col = 1; col <= N; col++) {
            // 가로의 합을 sumMap 갱신
            for (int row = 1; row <= N; row++) {
                sumMap[row][col] = sumMap[row][col - 1] + map[row][col];
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