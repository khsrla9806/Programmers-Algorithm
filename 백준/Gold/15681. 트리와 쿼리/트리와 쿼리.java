import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] subNodeCount;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 입력 값을 정리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] NRQ = reader.readLine().split(" ");
        int N = Integer.parseInt(NRQ[0]);
        int R = Integer.parseInt(NRQ[1]);
        int Q = Integer.parseInt(NRQ[2]);

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Node firstNode = nodes[Integer.parseInt(tokenizer.nextToken())];
            Node secondNode = nodes[Integer.parseInt(tokenizer.nextToken())];

            firstNode.children.add(secondNode);
            secondNode.children.add(firstNode);
        }

        Node rootNode = nodes[R];
        visited = new boolean[N + 1];
        subNodeCount = new int[N + 1];
        search(rootNode);

        StringBuilder answerBuilder = new StringBuilder();
        // 쿼리 시작
        for (int i = 0; i < Q; i++) {
            int queryValue = Integer.parseInt(reader.readLine());
            answerBuilder.append(subNodeCount[queryValue]).append("\n");
        }
        System.out.println(answerBuilder);
    }

    private static int search(Node node) {
        visited[node.value] = true;
        int count = 1;

        if (node.children.isEmpty()) {
            subNodeCount[node.value] = count;
            return count;
        }

        for (Node n : node.children) {
            if (!visited[n.value]) {
                count += search(n);
            }
        }
        subNodeCount[node.value] = count;

        return subNodeCount[node.value];
    }
}

class Node {
    int value;
    ArrayList<Node> children;

    public Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}
