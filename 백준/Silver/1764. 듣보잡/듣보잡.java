import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<String> names = new PriorityQueue<>();
        for (int i = 1; i <= (n + m); i++) {
            String name = br.readLine();
            if (i <= n) {
                map.put(name, 1);
            } else {
                if (map.containsKey(name)) {
                    names.offer(name);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(names.size()).append("\n");
        while (!names.isEmpty()) {
            sb.append(names.poll()).append("\n");
        }
        System.out.println(sb);
    }
}