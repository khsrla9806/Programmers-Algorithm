import java.io.*;
import java.util.*;

public class Main {
    private static final String NEW_LINE = "\n";
    private static final String BLANK = "";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        NicknameCounts nicknameCounts = new NicknameCounts();
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String nickname = reader.readLine();
            nicknameCounts.insert(nickname);

            int matchedLastIndex = trie.searchMatchLastIndex(nickname);
            if (matchedLastIndex == nickname.length() - 1) { // 포함되어 있는 단어
                int x = nicknameCounts.getCount(nickname);
                answer.append(nickname).append(x > 1 ? x : BLANK).append(NEW_LINE);
            } else if (matchedLastIndex > -1) { // 겹치는 prefix 존재
                answer.append(nickname, 0, matchedLastIndex + 2).append(NEW_LINE);
            } else if (matchedLastIndex == -1) { // 겹치는 prefix 없음
                answer.append(nickname.charAt(0)).append(NEW_LINE);
            }
            
            trie.insert(nickname);
        }
        System.out.println(answer);
    }
}

class TrieNode {
    Map<Character, TrieNode> childNodes;
    boolean isTerminated;

    public TrieNode() {
        this.childNodes = new HashMap<>();
        this.isTerminated = false;
    }


}

class Trie {
    TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = rootNode;
        for (char ch : word.toCharArray()) {
            node = node.childNodes.computeIfAbsent(ch, key -> new TrieNode());
        }
        node.isTerminated = true;
    }

    public int searchMatchLastIndex(String word) {
        int index = -1;
        TrieNode node = rootNode;
        for (char ch : word.toCharArray()) {
            node = node.childNodes.getOrDefault(ch, null);

            if (node == null) {
                break;
            }
            index++;
        }
        return index;
    }

    public boolean contains(String word) {
        TrieNode node = rootNode;
        for (char ch : word.toCharArray()) {
            node = node.childNodes.getOrDefault(ch, null);

            if (node == null) {
                return false;
            }
        }
        return node.isTerminated; // last character -> contains word in trie -> true
    }

    public boolean isEmpty() {
        return rootNode.childNodes.isEmpty();
    }
}

class NicknameCounts {
    private final Map<String, Integer> nicknameCounts;

    public NicknameCounts() {
        this.nicknameCounts = new HashMap<>();
    }

    public void insert(String nickname) {
        if (nicknameCounts.containsKey(nickname)) {
            nicknameCounts.put(nickname, nicknameCounts.get(nickname) + 1);
            return;
        }
        nicknameCounts.put(nickname, 1);
    }

    public int getCount(String nickname) {
        return nicknameCounts.getOrDefault(nickname, 0);
    }

    public boolean contains(String nickname) {
        return nicknameCounts.containsKey(nickname);
    }
}
