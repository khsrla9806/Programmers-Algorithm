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

        for (int i = 0; i < M; i++) {
            String[] priorityInfo = reader.readLine().split(" ");
            int firstIdx = Integer.parseInt(priorityInfo[0]) - 1;
            int secondIdx = Integer.parseInt(priorityInfo[1]) - 1;
            nodes[secondIdx].degree++;
            nodes[firstIdx].linkedNodes.add(nodes[secondIdx]);
        }

        StringBuilder answerBuilder = new StringBuilder();
        Queue<Node> answerQueue = new LinkedList<>();
        for (Node n : nodes) {
            if (n.degree == 0) {
                answerQueue.add(n);
            }
        }

        while (!answerQueue.isEmpty()) {
            Node node = answerQueue.poll();
            answerBuilder.append(node.value).append(" ");
            node.linkedNodes.forEach(n -> {
                n.degree--;
                if (n.degree == 0) answerQueue.add(n);
            });
        }

        System.out.println(answerBuilder);
    }
}

class Node implements Comparable<Node> {
    int value;
    int degree;
    List<Node> linkedNodes;

    public Node(int value) {
        this.value = value;
        this.linkedNodes = new ArrayList<>();
    }

    public int compareTo(Node other) {
        return this.degree - other.degree;
    }
}