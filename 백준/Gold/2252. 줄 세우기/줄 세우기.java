import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = reader.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }

        // 진입 차수를 설정
        for (int i = 0; i < M; i++) {
            String[] priorityInfo = reader.readLine().split(" ");
            Node firstNode = nodes[Integer.parseInt(priorityInfo[0]) - 1];
            Node secondNode = nodes[Integer.parseInt(priorityInfo[1]) - 1];
            firstNode.linkedNodes.add(secondNode);
            secondNode.degree++;
        }

        // 위상정렬 시작
        StringBuilder answerBuilder = new StringBuilder();
        Queue<Node> answerQueue = new LinkedList<>();
        // 진입차수가 0인 노드를 모두 큐에 넣음
        for (Node n : nodes) {
            if (n.degree == 0) {
                answerQueue.add(n);
            }
        }

        while (!answerQueue.isEmpty()) {
            Node node = answerQueue.poll();
            answerBuilder.append(node.value).append(" "); // 큐에서 뽑은 순서대로 정답에 넣음
            node.linkedNodes.forEach(n -> {
                if (--n.degree == 0) answerQueue.add(n); // 연결된 부분을 끊어주면서 진입차수를 감소시킨 차수가 0이면 큐에 넣음
            });
        }

        System.out.println(answerBuilder);
    }
}

class Node {
    int value;
    int degree;
    List<Node> linkedNodes;

    public Node(int value) {
        this.value = value;
        this.linkedNodes = new ArrayList<>();
    }
}