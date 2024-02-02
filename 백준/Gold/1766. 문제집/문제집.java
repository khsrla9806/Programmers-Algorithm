import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = reader.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new Node(i);

        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Node leadNode = nodes[Integer.parseInt(tokenizer.nextToken())];
            Node followNode = nodes[Integer.parseInt(tokenizer.nextToken())];
            leadNode.followNodes.add(followNode);
            followNode.entryCount++;
        }

        // 위상정렬을 위해서 진입차수가 0인 노드를 큐에 삽입
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (nodes[i].entryCount == 0) {
                queue.add(nodes[i]);
            }
        }

        // 위상정렬 시작
        StringBuilder answer = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            answer.append(node.value).append(" ");
            for (Node followNode : node.followNodes) {
                if (--followNode.entryCount == 0) {
                    queue.add(followNode);
                }
            }
        }
        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int value;
    int entryCount;
    List<Node> followNodes;

    public Node(int value) {
        this.value = value;
        this.entryCount = 0;
        this.followNodes = new ArrayList<>();
    }

    @Override
    public int compareTo(Node node) {
        return this.value - node.value;
    }
}
