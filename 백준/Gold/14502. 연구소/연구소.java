import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map, direct = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;
    static ArrayList<int[]> virus = new ArrayList<>(), blank = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                int[] cors = {i, j};
                map[i][j] = value;
                if (value == 2) {
                    virus.add(cors);
                } else if (value == 0) {
                    blank.add(cors);
                }
            }
        }

        int count = blank.size() - 3; // 벽의 개수 빼기
        for (int i = 0; i < blank.size() - 2; i++) {
            int[] firstWall = blank.get(i);
            map[firstWall[0]][firstWall[1]] = 1;
            for (int j = i + 1; j < blank.size() - 1; j++) {
                int[] secondWall = blank.get(j);
                map[secondWall[0]][secondWall[1]] = 1;
                for (int k = j + 1; k < blank.size(); k++) {
                    int[] thirdWall = blank.get(k);
                    map[thirdWall[0]][thirdWall[1]] = 1;
                    visited = new boolean[N][M];
                    bfs(count);
                    map[thirdWall[0]][thirdWall[1]] = 0;
                }
                map[secondWall[0]][secondWall[1]] = 0;
            }
            map[firstWall[0]][firstWall[1]] = 0;
        }
        System.out.println(answer);
    }

    public static void bfs(int count) {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] v : virus) {
            queue.offer(v);

            while (!queue.isEmpty()) {
                int[] cors = queue.poll();
                visited[cors[0]][cors[1]] = true;

                for (int[] d : direct) {
                    int nextRow = cors[0] + d[0];
                    int nextCol = cors[1] + d[1];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M) {
                        if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[]{nextRow, nextCol});
                            count--;
                        }
                    }
                }
            }
        }
        answer = Math.max(answer, count);
    }
}