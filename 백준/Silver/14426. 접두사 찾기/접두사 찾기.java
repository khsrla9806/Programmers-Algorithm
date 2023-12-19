import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData = reader.readLine().split(" ");
        int N = Integer.parseInt(inputData[0]);
        int M = Integer.parseInt(inputData[1]);
        Trie trie = new Trie();

        // Save Elements in Trie Node
        for (int i = 0; i < N; i++) {
            trie.insert(reader.readLine());
        }

        // Search Answers
        int answer = 0;
        for (int i = 0; i < M; i++) {
            String word = reader.readLine();
            if (trie.contains(word)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

class TrieNode {
    Map<Character, TrieNode> childNodes;
    boolean isTerminated;

    public TrieNode() {
        this.childNodes = new HashMap<>();
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.childNodes.computeIfAbsent(ch, key -> new TrieNode());
        }
        node.isTerminated = true;
    }

    public boolean contains(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.childNodes.getOrDefault(ch, null);
            if (node == null) {
                return false;
            }
        }
        return true;
    }
}
