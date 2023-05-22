import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static TreeMap<String, String[]> tree;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new TreeMap<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
            tree.put(parent, new String[]{left, right});
        }
        preorder("A");
        builder.append("\n");
        inorder("A");
        builder.append("\n");
        postorder("A");
        builder.append("\n");
        System.out.println(builder);
    }

    public static void preorder(String node) {
        if (node.equals(".")) {
            return;
        }
        builder.append(node);
        preorder(tree.get(node)[0]);
        preorder(tree.get(node)[1]);
    }

    public static void inorder(String node) {
        if (node.equals(".")) {
            return;
        }
        inorder(tree.get(node)[0]);
        builder.append(node);
        inorder(tree.get(node)[1]);
    }

    public static void postorder(String node) {
        if (node.equals(".")) {
            return;
        }
        postorder(tree.get(node)[0]);
        postorder(tree.get(node)[1]);
        builder.append(node);
    }
}