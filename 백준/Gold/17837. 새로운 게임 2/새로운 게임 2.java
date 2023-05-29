import java.io.*;
import java.util.*;

public class Main {
    static final int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static LinkedList<Node>[][] map;
    static int[][] colorInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new LinkedList[N + 1][N + 1];
        colorInfo = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                colorInfo[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new LinkedList<>();
            }
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int[] curr = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] direction = getDirection(Integer.parseInt(st.nextToken()));
            Node node = new Node(i, curr, direction);
            nodes.add(node);
            map[curr[0]][curr[1]].add(node);
        }

        // 게임 시작
        for (int t = 1; t <= 1000; t++) {
            for (Node node : nodes) {
                int[] nextCur = node.getNextCurr();
                if (isInvalidOrBlue(nextCur)) {
                    node.reverseDirection();
                    nextCur = node.getNextCurr();
                    if (isInvalidOrBlue(nextCur)) continue;
                }
                int index = currentIndex(node);
                int curRow = node.curr[0];
                int curCol = node.curr[1];
                while (map[curRow][curCol].size() > index) {
                    Node temp = null;
                    if (colorInfo[nextCur[0]][nextCur[1]] == WHITE) {
                        temp = map[curRow][curCol].remove(index);
                    } else if (colorInfo[nextCur[0]][nextCur[1]] == RED) {
                        temp = map[curRow][curCol].removeLast();
                    }
                    temp.curr = nextCur;
                    map[nextCur[0]][nextCur[1]].add(temp);
                }

                if (map[nextCur[0]][nextCur[1]].size() >= 4) {
                    System.out.println(t);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    public static int currentIndex(Node node) {
        for (int i = 0; i < map[node.curr[0]][node.curr[1]].size(); i++) {
            if (map[node.curr[0]][node.curr[1]].get(i).num == node.num) {
                return i;
            }
        }
        return -1;
    }

    public static int[] getDirection(int option) {
        int[] direction = {0, 0};
        if (option == 1) direction[1] = 1;
        else if (option == 2) direction[1] = -1;
        else if (option == 3) direction[0] = -1;
        else direction[0] = 1;

        return direction;
    }

    public static boolean isInvalidOrBlue(int[] curr) {
        if (curr[0] <= 0 || curr[1] <= 0 || curr[0] > N || curr[1] > N) {
            return true;
        }
        return colorInfo[curr[0]][curr[1]] == BLUE;
    }
}

class Node {
    int num;
    int[] curr;
    int[] direction;

    public Node(int num, int[] curr, int[] direction) {
        this.num = num;
        this.curr = curr;
        this.direction = direction;
    }

    public void reverseDirection() {
        this.direction[0] *= -1;
        this.direction[1] *= -1;
    }

    public int[] getNextCurr() {
        return new int[]{curr[0] + direction[0], curr[1] + direction[1]};
    }
}
