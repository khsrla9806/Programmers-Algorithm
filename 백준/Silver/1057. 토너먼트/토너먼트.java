import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int n;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        visited = new boolean[n + 1];
        int kim = Integer.parseInt(input[1]);
        int lim = Integer.parseInt(input[2]);
        int round = 1;

        Deque<Integer> players = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            players.offer(i);
        }

        while (!players.isEmpty()) {
            if (players.size() == 1) {
                round = -1;
                break;
            }
            int first = players.poll();
            int second = players.peek();

            if (visited[second]) {
                if (!visited[first]) {
                    players.addLast(first);
                } else {
                    players.addFirst(first);
                }
                round++;
                visited = new boolean[n + 1];
                continue;
            }

            second = players.poll();

            visited[first] = true;
            visited[second] = true;

            if ((first == kim || second == kim) && (first == lim || second == lim)) {
                break;
            }

            if (first == kim || first == lim) {
                players.offer(first);
            } else if (second == kim || second == lim) {
                players.offer(second);
            } else {
                players.offer(first); // 사실 어떤 것이 들어가도 상관없음
            }
        }

        System.out.println(round);
    }
}