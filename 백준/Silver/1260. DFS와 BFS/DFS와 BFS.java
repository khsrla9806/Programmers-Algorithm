import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static StringBuilder builder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        n = Integer.parseInt(inputData[0]);
        int m = Integer.parseInt(inputData[1]);
        int v = Integer.parseInt(inputData[2]);

        // TODO: 그래프 만들기
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            inputData = reader.readLine().split(" ");
            int startNum = Integer.parseInt(inputData[0]);
            int endNum = Integer.parseInt(inputData[1]);

            graph[startNum][endNum] = 1;
            graph[endNum][startNum] = 1;
        }
        builder = new StringBuilder();
        visited = new boolean[n + 1];
        dfs(v, graph);
        builder.append("\n");
        visited = new boolean[n + 1];
        bfs(v, graph);
        System.out.println(builder);
    }

    // TODO: BFS 탐색 (Queue)
    public static void bfs(int start, int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vortex = queue.poll();
            builder.append(vortex).append(" ");
            int[] vortexWithNodes = graph[vortex];

            for (int i = 1; i <= n; i++) {
                if (vortexWithNodes[i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    // TODO: DFS 탐색 (재귀)
    public static void dfs(int start, int[][] graph) {
        visited[start] = true;
        builder.append(start).append(" ");
        int[] startWithNodes = graph[start];

        for (int i = 1; i <= n; i++) {
            if (startWithNodes[i] == 1 && !visited[i]) {
                dfs(i, graph);
            }
        }
    }
}