import java.io.*;

public class Main {
    static int answer, target;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            answer = 0;
            target = Integer.parseInt(reader.readLine());
            for (int j = 1; j <= target; j++) {
                find(0, 0, j);
            }
            builder.append(answer).append("\n");
        }
        System.out.println(builder);
    }

    public static void find(int sum, int cnt, int goal) {
        if (cnt == goal) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            find(sum + i, cnt + 1, goal);
        }
    }
}