import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        int index = 0;

        int[] data = new int[count];
        for (String value : br.readLine().split(" ")) {
            data[index++] = Integer.parseInt(value);
        }
        Arrays.sort(data);
        System.out.println(data[0] * data[count - 1]);
    }
}