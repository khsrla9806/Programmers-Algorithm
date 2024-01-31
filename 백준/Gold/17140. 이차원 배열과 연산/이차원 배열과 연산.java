import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    static int colCount, rowCount;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        // 입력값 초기화
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] rck = reader.readLine().split(" ");
        int r = Integer.parseInt(rck[0]);
        int c = Integer.parseInt(rck[1]);
        int k = Integer.parseInt(rck[2]);
        map = new int[101][101];
        colCount = 3;
        rowCount = 3;
        for (int i = 1; i <= 3; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 1; j <= 3; j++) {
                map[i][j] = Integer.parseInt(line[j - 1]);
            }
        }

        // 로직 시작
        int second = 0;
        if (map[r][c] == k) {
            System.out.println(second);
            return;
        }

        while (map[r][c] != k) {
            if (++second > 100) {
                System.out.println(-1);
                return;
            }

            if (colCount <= rowCount) {
                operationR();
            } else {
                operationC();
            }
        }
        System.out.println(second);
    }

    private static void operationR() {
        int nextColCount = 0;
        for (int row = 1; row <= 100; row++) {
            HashMap<Integer, Node> nodeMap = new HashMap<>();

            for (int col = 1; col <= 100; col++) {
                int value = map[row][col];
                if (value == 0) continue;
                map[row][col] = 0;

                if (nodeMap.containsKey(value)) {
                    nodeMap.get(value).appearCount++;
                    continue;
                }
                nodeMap.put(value, new Node(value, 1));
            }

            PriorityQueue<Node> queue = new PriorityQueue<>(nodeMap.values());
            int size = Math.min(queue.size() * 2, 100);
            nextColCount = Math.max(nextColCount, size);
            for (int col = 1; col < size; col += 2) {
                Node node = queue.poll();
                map[row][col] = node.value;
                map[row][col + 1] = node.appearCount;
            }
        }
        colCount = nextColCount;
    }

    private static void operationC() {
        int nextRowCount = 0;
        for (int col = 1; col <= 100; col++) {
            HashMap<Integer, Node> nodeMap = new HashMap<>();

            for (int row = 1; row <= 100; row++) {
                int value = map[row][col];
                if (value == 0) continue;
                map[row][col] = 0;

                if (nodeMap.containsKey(value)) {
                    nodeMap.get(value).appearCount++;
                    continue;
                }
                nodeMap.put(value, new Node(value, 1));
            }

            PriorityQueue<Node> queue = new PriorityQueue<>(nodeMap.values());
            int size = Math.min(queue.size() * 2, 100);
            nextRowCount = Math.max(nextRowCount, size);
            for (int row = 1; row < size; row += 2) {
                Node node = queue.poll();
                map[row][col] = node.value;
                map[row + 1][col] = node.appearCount;
            }
        }
        rowCount = nextRowCount;
    }
}

class Node implements Comparable<Node> {
    int value;
    int appearCount;

    public Node(int value, int appearCount) {
        this.value = value;
        this.appearCount = appearCount;
    }

    @Override
    public int compareTo(Node node) {
        if (this.appearCount == node.appearCount) {
            return this.value - node.value;
        }

        return this.appearCount - node.appearCount;
    }

    public String toString() {
        return String.format("(%d, %d)", value, appearCount);
    }
}
