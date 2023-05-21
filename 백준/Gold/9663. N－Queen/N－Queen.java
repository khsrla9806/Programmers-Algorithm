import java.io.*;

public class Main {
    static int N, answer;
    static int[] queens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queens = new int[N];
        find(0);
        System.out.println(answer);
    }

    public static void  find(int col) {
        if (col == N) {
            answer++;
            return;
        }
        for (int r = 0; r < N; r++) {
            queens[col] = r;

            if (isPossible(col)) {
                find(col + 1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            if (queens[col] == queens[i]) {
                return false;
            }

            if (Math.abs(col - i) == Math.abs(queens[col] - queens[i])) {
                return false;
            }
        }
        return true;
    }
}