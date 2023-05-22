import java.io.*;
import java.util.*;

public class Main {
    static int[][] DIRECT = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] TOP_CLEANER = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] BOTTOM_CLEANER = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int R, C, T;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        int[] top = null; // 반시계 방향
        int[] bottom = null; // 시계 방향
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    int[] cors = {i, j};
                    if (top == null) {
                        top = cors;
                    } else {
                        bottom = cors;
                    }
                }
            }
        }

        Queue<int[]> queue;
        for (int i = 0; i < T; i++) {
            queue = new LinkedList<>();
            for (int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    if (map[row][col] >= 5) {
                        int[] dust = {row, col, map[row][col]};
                        queue.offer(dust);
                    }
                }
            }

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int weight = curr[2] / 5;
                for (int[] direct : DIRECT) {
                    int nextR = curr[0] + direct[0];
                    int nextC = curr[1] + direct[1];

                    if (isValid(nextR, nextC) && map[nextR][nextC] != -1) {
                        map[nextR][nextC] += weight;
                        map[curr[0]][curr[1]] -= weight;
                    }
                }
            }

            // 공기 청정기 위쪽 작동
            int[] prev = top;
            for (int[] direct : TOP_CLEANER) {
                int nextR = prev[0] + direct[0];
                int nextC = prev[1] + direct[1];
                while (nextR >= 0 && nextC >= 0 && nextR <= top[0] && nextC < C) {
                    if (map[prev[0]][prev[1]] == -1) {
                        map[nextR][nextC] = 0;
                    } else {
                        int next = map[nextR][nextC];
                        map[prev[0]][prev[1]] = next == -1 ? 0 : next;
                    }
                    prev = new int[]{nextR, nextC};
                    nextR = prev[0] + direct[0];
                    nextC = prev[1] + direct[1];
                }
            }

            // 공기 청정기 아래쪽 작동
            prev = bottom;
            for (int[] direct : BOTTOM_CLEANER) {
                int nextR = prev[0] + direct[0];
                int nextC = prev[1] + direct[1];
                while (nextR >= bottom[0] && nextC >= 0 && nextR < R && nextC < C) {
                    if (map[prev[0]][prev[1]] == -1) {
                        map[nextR][nextC] = 0;
                    } else {
                        int next = map[nextR][nextC];
                        map[prev[0]][prev[1]] = next == -1 ? 0 : next;
                    }
                    prev = new int[]{nextR, nextC};
                    nextR = prev[0] + direct[0];
                    nextC = prev[1] + direct[1];
                }
            }

        }

        int answer = 0;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] > 0) {
                    answer += map[row][col];
                }
            }
        }
        System.out.println(answer);
    }

    public static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < R && col < C;
    }
}