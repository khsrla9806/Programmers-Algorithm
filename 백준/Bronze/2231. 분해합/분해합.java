import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(reader.readLine());
        int answer = 0;

        for (int i = 1; i < m; i++) {
            String[] str = String.valueOf(i).split("");
            int sum = i;
            for (String s : str) {
                sum += Integer.parseInt(s);
            }

            if (sum == m) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}