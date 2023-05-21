import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];
        combination(visited, 0, 1, M);
        System.out.println(sb);
    }

    public static void combination(boolean[] visited, int count, int start, int M) {
        if (count == M) {
            for (int i = 1; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
        }

        for (int i = start; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(visited, count + 1, i + 1, M);
                visited[i] = false;
            }
        }
    }
}
