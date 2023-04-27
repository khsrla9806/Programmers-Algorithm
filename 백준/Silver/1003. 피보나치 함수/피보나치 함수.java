import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        int t = Integer.parseInt(reader.readLine());
        int[] zeroCnt = new int[41];
        int[] oneCnt = new int[41];

        zeroCnt[0] = 1;
        zeroCnt[1] = 0;
        oneCnt[0] = 0;
        oneCnt[1] = 1;

        for (int i = 2; i <= 40; i++) {
            zeroCnt[i] = zeroCnt[i - 1] + zeroCnt[i - 2];
            oneCnt[i] = oneCnt[i - 1] + oneCnt[i - 2];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            builder.append(zeroCnt[n]).append(" ").append(oneCnt[n]).append("\n");
        }

        System.out.println(builder);
    }
}