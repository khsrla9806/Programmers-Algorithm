import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String[] data = reader.readLine().split(" ");
        int result = data.length;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(data[i]);
            if (num == 1) result--;
            for (int j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    result--;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}