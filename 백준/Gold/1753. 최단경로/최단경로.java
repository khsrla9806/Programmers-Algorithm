import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K;
    static int[] dist;
    static List<Node>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        map = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            map[i] = new ArrayList<>();
        }
        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map[from].add(new Node(to, weight));
        }

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 탐색
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        nodes.offer(new Node(K, 0));
        dist[K] = 0;

        while (!nodes.isEmpty()) {
            Node curr = nodes.poll();

            if (visited[curr.end]) {
                continue;
            }
            visited[curr.end] = true;
            for (Node node : map[curr.end]) {
                int nextDis = dist[curr.end] + node.weight;
                if (nextDis < dist[node.end]) {
                    dist[node.end] = nextDis;
                    nodes.offer(new Node(node.end, dist[node.end]));
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }
        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}