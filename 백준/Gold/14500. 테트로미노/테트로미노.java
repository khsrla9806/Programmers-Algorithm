import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, rowAndCols = new int[4][2];
    static int[][][] blockInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        setBlockInfo();
        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                answer = Math.max(answer, getMaxSumResultOfBlocks(row, col));
            }
        }
        System.out.println(answer);
    }

    public static void setBlockInfo() {
        blockInfo = new int[][][] {
                // first
                {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
                {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
                // second
                {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
                // third
                {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {0, 2}},
                {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
                {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
                {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
                {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
                // fourth
                {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
                {{0, 0}, {0, 1}, {1, 0}, {1, -1}},
                {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
                // fifth
                {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
                {{0, 0}, {0, 1}, {-1, 1}, {1, 1}},
                {{0, 0}, {1, 0}, {1, -1}, {1, 1}},
                {{0, 0}, {1, 0}, {2, 0}, {1, 1}}
        };
    }

    public static boolean isValid() {
        for (int[] rowAndCol : rowAndCols) {
            int row = rowAndCol[0];
            int col = rowAndCol[1];
            if (row < 0 || col < 0 || row >= N || col >= M) {
                return false;
            }
        }
        return true;
    }

    public static int getMaxSumResultOfBlocks(int row, int col) {
        int result = 0;
        for (int[][] info : blockInfo) {
            for (int j = 0; j < 4; j++) {
                rowAndCols[j][0] = row + info[j][0];
                rowAndCols[j][1] = col + info[j][1];
            }

            int sum = 0;
            if (isValid()) {
                for (int[] rowAndCol : rowAndCols) {
                    int r = rowAndCol[0];
                    int c = rowAndCol[1];
                    sum += map[r][c];
                }
                result = Math.max(result, sum);
            }
        }
        return result;
    }
}