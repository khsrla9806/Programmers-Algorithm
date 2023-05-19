import java.io.*;
import java.util.*;

public class Main {
    static int[] locations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        locations = new int[100001];
        Arrays.fill(locations, Integer.MAX_VALUE);
        search(N, K);
        System.out.println(locations[K]);
    }

    public static void search(int N, int K) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{N, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int depth = curr[1];
            if (pos == K) {
                locations[pos] = depth;
                break;
            }
            if (locations[pos] != Integer.MAX_VALUE) continue;
            if (locations[pos] > depth) {
                locations[pos] = depth;
            }
            if (pos < K && isValid(pos + 1)) {
                queue.offer(new int[]{pos + 1, locations[pos] + 1});
            }
            if (isValid(pos - 1)) {
                queue.offer(new int[]{pos - 1, locations[pos] + 1});
            }
            if (pos < K && isValid(pos * 2)) {
                queue.offer(new int[]{pos * 2, locations[pos] + 1});
            }
        }
    }

    public static boolean isValid(int N) {
        if (N < 0 || N > 100000) return false;
        return true;
    }
}
