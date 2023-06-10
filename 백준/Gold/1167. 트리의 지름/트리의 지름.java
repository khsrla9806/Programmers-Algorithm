import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static List<Node>[] map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        map = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                map[start].add(new Node(end, dist));
            }
        }

        // 1번 부터 탐색해서 가장 거리가 긴 노드를 찾는다.
        visited = new boolean[V + 1];
        Node bigNode = bfs(1);

        // 가장 긴 노드부터 시작해서 최장 거리를 찾음
        visited = new boolean[V + 1];
        Node answer = bfs(bigNode.end);

        System.out.println(answer.dist);
    }

    public static Node bfs(int startNum) {
        Queue<Node> nodes = new LinkedList<>();
        Node result = new Node(startNum, 0);
        nodes.add(result);
        visited[startNum] = true;

        while (!nodes.isEmpty()) {
            Node curr = nodes.poll();

            if (result.dist < curr.dist) {
                result = curr;
            }

            for (Node node : map[curr.end]) {
                if (!visited[node.end]) {
                    nodes.add(new Node(node.end, curr.dist + node.dist));
                    visited[node.end] = true;
                }
            }
        }
        return result;
    }

    static class Node {
        int end, dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
}