import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(arr);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(arr[i]).append("\n");
        }
        System.out.println(builder);
    }
}