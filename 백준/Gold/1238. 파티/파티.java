import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<Node>[] map, reverseMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new ArrayList[N + 1];
        reverseMap = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
            reverseMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            map[start].add(new Node(end, dist));
            reverseMap[end].add(new Node(start, dist));
        }
        int[] go = dijkstra(reverseMap); // 각 노드에서 X로 가는 최단 경로
        int[] back = dijkstra(map); // X에서 각 노드로 가는 최단 경로

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, go[i] + back[i]);
        }
        System.out.println(answer);
    }

    public static int[] dijkstra(List<Node>[] inputMap) {
        boolean[] visited = new boolean[N + 1];
        int[] result = new int[N + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[X] = 0; // X에서 X로 가는 경로는 0으로 초기화

        // 다익스트라 알고리즘 시작
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.dist - n2.dist);
        queue.add(new Node(X, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (visited[curr.end]) {
                continue;
            }
            visited[curr.end] = true;
            for (Node node : inputMap[curr.end]) {
                if (curr.dist + node.dist < result[node.end]) {
                    result[node.end] = curr.dist + node.dist;
                }
                queue.add(new Node(node.end, result[node.end]));
            }
        }
        return result;
    }

    static class Node {
        int end, dist;

        public Node (int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
}