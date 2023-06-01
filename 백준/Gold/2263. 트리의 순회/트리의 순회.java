import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] inorder, postorder, preorder;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer in  = new StringTokenizer(br.readLine());
        StringTokenizer post = new StringTokenizer(br.readLine());

        inorder = new int[n];
        postorder = new int[n];
        preorder = new int[n];
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(in.nextToken());
            postorder[i] = Integer.parseInt(post.nextToken());
        }

        findPreorder(0, n - 1, 0, n - 1);
        System.out.println(builder);
    }

    public static void findPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        // root 찾기
        int root = postorder[postEnd];
        builder.append(root).append(" ");

        // root index 찾기
        int rootIndex = findRootIndex(inStart, inEnd, root);

        // root 기준으로 왼쪽에 존재하는 요소 개수
        int leftCount = rootIndex - inStart;

        // 좌측
        findPreorder(inStart, rootIndex - 1, postStart, postStart + leftCount - 1);
        // 우측
        findPreorder(rootIndex + 1, inEnd, postStart + leftCount, postEnd - 1);
    }

    public static int findRootIndex(int inStart, int inEnd, int root) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root) return i;
        }
        return -1;
    }
}
