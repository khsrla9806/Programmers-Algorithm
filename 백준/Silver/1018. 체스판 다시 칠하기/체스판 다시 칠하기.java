import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int M = Integer.parseInt(tokenizer.nextToken());

        char[][] panelMap = new char[N][M];
        for (int i = 0; i < N; i++) {
            panelMap[i] = reader.readLine().toCharArray();
        }

        int answer = Integer.MAX_VALUE;

        for (int k = 0; k <= N - 8; k++) {
            for (int u = 0; u <= M - 8; u++) {
                int firstColorB = 0;
                int firstColorW = 0;
                for (int i = k; i < k + 8; i++) {
                    for (int j = u; j < u + 8; j++) {
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                if (panelMap[i][j] == 'W') {
                                    firstColorB++;
                                } else if (panelMap[i][j] == 'B') {
                                    firstColorW++;
                                }
                            }

                            if (j % 2 != 0) {
                                if (panelMap[i][j] == 'B') {
                                    firstColorB++;
                                } else if (panelMap[i][j] == 'W') {
                                    firstColorW++;
                                }
                            }
                        } else {
                            if (j % 2 == 0) {
                                if (panelMap[i][j] == 'B') {
                                    firstColorB++;
                                } else if (panelMap[i][j] == 'W') {
                                    firstColorW++;
                                }
                            }

                            if (j % 2 != 0) {
                                if (panelMap[i][j] == 'W') {
                                    firstColorB++;
                                } else if (panelMap[i][j] == 'B') {
                                    firstColorW++;
                                }
                            }
                        }
                    }
                }
                answer = Math.min(Math.min(answer, firstColorB), firstColorW);
            }
        }

        System.out.println(answer);
    }
}