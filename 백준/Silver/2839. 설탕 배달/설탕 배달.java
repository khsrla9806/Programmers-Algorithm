import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= (n / 5); i++) {
            int total = i;
            int remain = n - (5 * i);
            if (remain % 3 == 0) {
                total += (remain / 3);
                answer = Math.min(answer, total);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}