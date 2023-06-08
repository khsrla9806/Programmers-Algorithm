import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1][2]; // (벽을 부수고 가는 경우, 벽을 부수지 않고 가는 경우)
        for (int row = 1; row <= N; row++) {
            char[] input = br.readLine().toCharArray();
            for (int col = 1; col <= M; col++) {
                map[row][col] = input[col - 1] - '0';
            }
        }
        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(new Node(1, 1, 1, false));
        visited[1][1][1] = true;

        while (!nodes.isEmpty()) {
            Node curr = nodes.poll();

            if (curr.row == N && curr.col == M) {
                return curr.dist;
            }

            int nextDist = curr.dist + 1;
            for (int[] m : move) {
                int nextRow = curr.row + m[0];
                int nextCol = curr.col + m[1];
                if (!isValid(nextRow, nextCol)) {
                    continue;
                }

                if (map[nextRow][nextCol] == 1) { // 벽인 경우
                    if (!curr.useBreak && !visited[nextRow][nextCol][0]) { // 아직 부순적이 없는 경우
                        nodes.add(new Node(nextRow, nextCol, nextDist, true));
                        visited[nextRow][nextCol][0] = true;
                    }
                } else if (map[nextRow][nextCol] == 0) { // 벽이 아닌 경우
                    if (curr.useBreak && !visited[nextRow][nextCol][0]) { // 벽을 부수고 가는 경우
                        nodes.add(new Node(nextRow, nextCol, nextDist, true));
                        visited[nextRow][nextCol][0] = true;
                    } else if (!curr.useBreak && !visited[nextRow][nextCol][1]) { // 벽을 부수지 않고 가는 경우
                        nodes.add(new Node(nextRow, nextCol, nextDist, false));
                        visited[nextRow][nextCol][1] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isValid(int row, int col) {
        return row >= 1 && col >= 1 && row <= N && col <= M;
    }

    static class Node {
        int row, col, dist;
        boolean useBreak;

        public Node (int row, int col, int dist, boolean useBreak) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.useBreak = useBreak;
        }
    }
}