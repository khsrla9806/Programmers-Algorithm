import java.io.*;
import java.util.*;

public class Main {

    static int n, start;
    static List<Node>[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map[first].add(new Node(second, weight));
            map[second].add(new Node(first, weight));
        }
        // 가장 큰 노드를 탐색
        visited = new boolean[n + 1];
        find(1);

        // 해당 노드부터 탐색을 시작해서 최장 거리를 찾음
        visited = new boolean[n + 1];
        int result = find(start);
        System.out.println(result);
    }

    public static int find(int startNodeNumber) {
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startNodeNumber, 0));
        visited[startNodeNumber] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (result < curr.weight) {
                result = curr.weight;
                start = curr.end;
            }

            for (Node node : map[curr.end]) {
                if (visited[node.end]) continue;
                queue.add(new Node(node.end, curr.weight + node.weight));
                visited[node.end] = true;
            }
        }

        return result;
    }

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}