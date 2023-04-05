import java.io.*;

public class Main {
    static int n;
    static boolean[] visited;
    static int[][] scoreData;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        visited = new boolean[n];
        scoreData = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] inputData = reader.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                scoreData[i][j] = Integer.parseInt(inputData[j]);
            }
        }

        makeTeam(0, 0);
        System.out.println(answer);
    }

    public static void makeTeam(int count, int playerNum) {
        if (count == n / 2) {
            getScoreDiff();
        }

        for (int i = playerNum; i < n; i++) {
            visited[i] = true;
            makeTeam(count + 1, i + 1);
            visited[i] = false;
        }
    }

    public static void getScoreDiff() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    startTeam += scoreData[i][j] + scoreData[j][i];
                }
                if (!visited[i] && !visited[j]) {
                    linkTeam += scoreData[i][j] + scoreData[j][i];
                }
            }
        }

        int diff = Math.abs(startTeam - linkTeam);
        answer = Math.min(answer, diff);
    }
}