import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 상, 하, 좌, 우
    static final int[][] DIRECT = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static boolean[][] hasBug;
    static int M, N, bugCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        // Test Case 수만큼 반복
        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            M = Integer.parseInt(input[0]); // 가로
            N = Integer.parseInt(input[1]); // 세로
            int K = Integer.parseInt(input[2]); // 배추 개수
            map = new int[N][M];
            hasBug = new boolean[N][M];

            // 배추가 존재하는 곳을 1로 초기화
            for (int j = 0; j < K; j++) {
                String[] cabbage = reader.readLine().split(" ");
                int cabbageM = Integer.parseInt(cabbage[0]);
                int cabbageN = Integer.parseInt(cabbage[1]);
                map[cabbageN][cabbageM] = 1;
            }

            bugCnt = 0;
            for (int j = 0; j < M; j++) { // 가로 좌표
                for (int k = 0; k < N; k++) { // 세로 좌표
                    if (map[k][j] == 0 || hasBug[k][j]) {
                        continue;
                    }
                    bfs(k, j);
                    bugCnt++;
                }
            }
            builder.append(bugCnt).append("\n");
        }
        System.out.println(builder);
    }

    public static void bfs(int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        hasBug[n][m] = true;
        queue.offer(new int[]{n, m});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] direct : DIRECT) {
                int moveN = curr[0] + direct[0];
                int moveM = curr[1] + direct[1];
                if (moveM >= 0 && moveM < M && moveN >= 0 && moveN < N) {
                    if (map[moveN][moveM] == 1 && !hasBug[moveN][moveM]) {
                        hasBug[moveN][moveM] = true;
                        queue.offer(new int[]{moveN, moveM});
                    }
                }
            }
        }
    }
}