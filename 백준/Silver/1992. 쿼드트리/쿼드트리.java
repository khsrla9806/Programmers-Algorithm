import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        String result = search(0, 0, N);
        System.out.println(result);
    }

    private static String search(int row, int col, int n) {
        int half = n / 2;
        int result = checkSameSquare(row, col, n);

        if (result == -1) {
            StringBuilder builder = new StringBuilder()
                    .append("(")
                    .append(search(row, col, half))
                    .append(search(row, col + half, half))
                    .append(search(row + half, col, half))
                    .append(search(row + half, col + half, half))
                    .append(")");

            return builder.toString();
        }

        return String.valueOf(result);
    }

    private static int checkSameSquare(int row, int col, int n) {
        int value = map[row][col];
        for (int r = row; r < row + n; r++) {
            for (int c = col; c < col + n; c++) {
                if (map[r][c] != value) {
                    return -1;
                }
            }
        }
        return value;
    }
}
