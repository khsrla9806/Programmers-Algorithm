import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] nodes;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new ArrayList[n + 1];
        result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstNnm = Integer.parseInt(st.nextToken());
            int secondNum = Integer.parseInt(st.nextToken());
            nodes[firstNnm].add(secondNum);
            nodes[secondNum].add(firstNnm);
        }

        result[1] = -1;
        find(1);
        StringBuilder builder = new StringBuilder();
        for (int num = 2; num <= n; num++) {
            builder.append(result[num]).append("\n");
        }
        System.out.println(builder);
    }

    public static void find(int parent) {
        for (int node : nodes[parent]) {
            if (result[node] == 0) {
                result[node] = parent;
                find(node);
            }
        }
    }
}