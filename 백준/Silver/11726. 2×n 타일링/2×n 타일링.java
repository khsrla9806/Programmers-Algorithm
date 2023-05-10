import java.io.*;

public class Main {
    static int n;
    static int[] map = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map[1] = 1;
        map[2] = 2;
        for (int i = 3; i <= n; i++) {
            map[i] = (map[i - 1] + map[i - 2]) % 10007;
        }
        System.out.println(map[n]);
    }
}