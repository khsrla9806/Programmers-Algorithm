import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

        StringBuilder builder = new StringBuilder();
        Collections.sort(list);
        for (int num : list) {
            builder.append(num).append("\n");
        }
        System.out.println(builder);
    }
}