import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] numbers, result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static Set<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        result = new int[M];
        visited = new boolean[N];
        find(0, M);
        for (String str : set) {
            sb.append(str).append("\n");
        }
        System.out.println(sb);
    }

    public static void find(int cnt, int target) {
        if (target == 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cnt; i++) {
                builder.append(result[i]).append(" ");
            }
            set.add(builder.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result[cnt] = numbers[i];
            find(cnt + 1, target - 1);
            visited[i] = false;
        }
    }
}