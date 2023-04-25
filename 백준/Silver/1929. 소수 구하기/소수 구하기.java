import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        String[] inputData = reader.readLine().split(" ");
        int m = Integer.parseInt(inputData[0]);
        int n = Integer.parseInt(inputData[1]);

        for (int i = m; i <= n; i++) {
            if (search(i)) {
                builder.append(i).append("\n");
            }
        }

        System.out.println(builder);
    }

    public static boolean search(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}