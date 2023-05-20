import java.io.*;
import java.util.*;

public class Main {
    static final int RED = 0, GREEN = 1, BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] colorMap = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                colorMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            int[] prevColors = colorMap[i - 1];

            for (int j = 0; j < 3; j++) {
                if (j == RED) {
                    int prevMinPrice = Math.min(prevColors[GREEN], prevColors[BLUE]);
                    colorMap[i][j] += prevMinPrice;
                } else if (j == GREEN) {
                    int prevMinPrice = Math.min(prevColors[RED], prevColors[BLUE]);
                    colorMap[i][j] += prevMinPrice;
                } else {
                    int prevMinPrice = Math.min(prevColors[GREEN], prevColors[RED]);
                    colorMap[i][j] += prevMinPrice;
                }
            }
        }
        int[] result = colorMap[n - 1];
        int answer = Math.min(result[RED], Math.min(result[GREEN], result[BLUE]));
        System.out.println(answer);
    }
}
