import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (String value : br.readLine().split(" ")) {
            int v = Integer.parseInt(value);
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
        System.out.println(min * max);
    }
}