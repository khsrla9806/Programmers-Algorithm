import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 1; i <= N - 1; i++) {
            boolean[] visited = new boolean[N + 1];
            combination(visited, 1, i);
        }
        System.out.println(answer);
    }

    private static void combination(boolean[] visited, int start, int r) {
        if (answer == 0) return;
        if (r == 0) {
            int startTeam = 0;
            int linkTeam = 0;

            for (int i = 1; i <= N - 1; i++) {
                for (int j = i; j <= N; j++) {
                    if (visited[i] && visited[j]) {
                        startTeam += map[i][j] + map[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        linkTeam += map[i][j] + map[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(startTeam - linkTeam));
            return;
        }
        for (int i = start; i <= N; i++) {
            visited[i] = true;
            combination(visited, i + 1, r - 1);
            visited[i] = false;
        }
    }
}
