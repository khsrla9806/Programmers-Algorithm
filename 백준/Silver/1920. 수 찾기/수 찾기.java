import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer;
        HashMap<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        while (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        int m = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        while (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            if (map.containsKey(value)) {
                builder.append(1);
            } else {
                builder.append(0);
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}