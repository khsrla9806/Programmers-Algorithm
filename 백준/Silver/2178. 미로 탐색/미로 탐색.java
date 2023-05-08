import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final int[][] DIRECT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static int n, m;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = reader.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] data = reader.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(data[j - 1]);
            }
        }

        visited = new boolean[n + 1][m + 1];
        System.out.println(bfs(new int[]{1, 1, 1}));;
    }

    public static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == n && curr[1] == m) {
                return curr[2];
            }

            for (int[] direct : DIRECT) {
                int y = curr[0] + direct[0];
                int x = curr[1] + direct[1];

                if (y < 1 || y > n || x < 1 || x > m || visited[y][x] || map[y][x] == 0) {
                    continue;
                }
                queue.offer(new int[] {y, x, curr[2] + 1});
                visited[y][x] = true;
            }
        }
        return -1;
    }
}