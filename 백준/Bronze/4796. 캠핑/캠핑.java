import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        for (int testCase = 1; ; testCase++) {
            String[] command = reader.readLine().split(" ");
            if (command[0].equals("0") && command[1].equals("0") && command[2].equals("0")) {
                break;
            }
            int L = Integer.parseInt(command[0]);
            int P = Integer.parseInt(command[1]);
            int V = Integer.parseInt(command[2]);
            int answer = (V / P) * L + Math.min(V % P, L);

            builder.append("Case ").append(testCase).append(": ").append(answer).append("\n");
        }
        System.out.println(builder);
    }
}
