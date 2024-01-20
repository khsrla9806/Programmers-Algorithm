import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] map, priceMap;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // 선택할 수 있는 땅들의 가격 정보를 저장
        priceMap = new int[N][N];
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                priceMap[i][j] += (map[i][j] + map[i - 1][j] + map[i + 1][j] + map[i][j - 1] + map[i][j + 1]);
            }
        }

        // priceMap에서 씨앗을 심을 3개의 좌표를 선택
        visited = new boolean[N + 1][N + 1];
        search(0, 0);

        System.out.println(answer);
    }

    private static void search(int depth, int sum) {
        if (depth == 3) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int r = 1; r < N - 1; r++) {
            for (int c = 1; c < N - 1; c++) {
                if (!visited[r][c]
                        && !visited[r + 1][c] && !visited[r - 1][c]
                        && !visited[r][c + 1] && !visited[r][c - 1]) {
                    setVisit(r, c);
                    int cost = priceMap[r][c];
                    search(depth + 1, sum + cost);
                    resetVisit(r, c);
                }
            }
        }
    }

    private static void setVisit(int row, int col) {
        visited[row][col] = true;
        visited[row + 1][col] = true;
        visited[row - 1][col] = true;
        visited[row][col + 1] = true;
        visited[row][col - 1] = true;
    }

    private static void resetVisit(int row, int col) {
        visited[row][col] = false;
        visited[row + 1][col] = false;
        visited[row - 1][col] = false;
        visited[row][col + 1] = false;
        visited[row][col - 1] = false;
    }

    /**
     * 입력된 3개의 좌표가 유효한지 확인
     */
    private static boolean isPossible(int[] c1, int[] c2, int[] c3) {
        // 각 점 사이의 거리가 2를 초과해야 한다.
        return getDistance(c1, c2) > 2 && getDistance(c1, c3) > 2 && getDistance(c2, c3) > 2;
    }

    private static double getDistance(int[] c1, int[] c2) {
        int diffRow = c1[0] - c2[0];
        int diffCol = c1[1] - c2[1];
        return Math.sqrt((diffRow * diffRow) + (diffCol * diffCol));
    }
}