import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0, computer;
    static boolean[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        computer = Integer.parseInt(reader.readLine());
        int linkCnt = Integer.parseInt(reader.readLine());
        map = new boolean[computer + 1][computer + 1];
        for (int i = 0; i < linkCnt; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int firstCom = Integer.parseInt(tokenizer.nextToken());
            int secondCom = Integer.parseInt(tokenizer.nextToken());
            map[firstCom][secondCom] = true;
            map[secondCom][firstCom] = true;
        }

        visited = new boolean[computer + 1];
        dfs(1);
        System.out.println(answer);
    }

    public static void dfs(int com) {
        visited[com] = true;

        for (int i = 1; i <= computer; i++) {
            if (map[com][i] && !visited[i]) {
                dfs(i);
                answer++;
            }
        }
    }
}