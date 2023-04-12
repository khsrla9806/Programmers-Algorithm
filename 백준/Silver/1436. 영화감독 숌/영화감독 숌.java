import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(reader.readLine());
        int start = 666;
        int count = 0;

        while (count != N) {
            if (String.valueOf(start++).contains("666")) {
                count++;
            }
        }

        System.out.println(start - 1);
    }
}