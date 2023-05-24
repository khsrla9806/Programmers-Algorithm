import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, answer;
    static int[] items;
    static boolean[] visited;
    static List<Node>[] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        locations = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) locations[i] = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            locations[firstNode].add(new Node(secondNode, distance));
            locations[secondNode].add(new Node(firstNode, distance));
        }

        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, search(i));
        }
        System.out.println(answer);
    }

    public static int search(int node) {
        int totalItem = 0;
        visited = new boolean[n + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        queue.add(new Node(node, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (!visited[curr.end]) {
                totalItem += items[curr.end];
                visited[curr.end] = true;
            }
            for (Node location : locations[curr.end]) {
                if (curr.weight + location.weight > m) continue;
                queue.add(new Node(location.end, curr.weight + location.weight));
            }
        }
        return totalItem;
    }
}

class Node {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}