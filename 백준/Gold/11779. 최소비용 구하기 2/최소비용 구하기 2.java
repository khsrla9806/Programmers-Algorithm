import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 값 정리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        int[][] costMap = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                costMap[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            costMap[start][end] = Math.min(costMap[start][end], cost);
        }
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());

        // 시작점부터 끝점까지의 최소 비용을 확인
        int[] map = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = Integer.MAX_VALUE;
        }

        ArrayList<Integer> answerVisited = null;
        Queue<Element> queue = new LinkedList<>();
        ArrayList<Integer> initVisited = new ArrayList<>();
        initVisited.add(start);
        queue.add(new Element(start, 0, initVisited));

        while (!queue.isEmpty()) {
            Element element = queue.poll();
            int startValue = element.startValue;
            int costSum = element.costSum;

            for (int endValue = 1; endValue <= n; endValue++) {
                if (costMap[startValue][endValue] != Integer.MAX_VALUE) {
                    int sum = costSum + costMap[startValue][endValue];
                    if (map[endValue] > sum) {
                        map[endValue] = sum;
                        ArrayList<Integer> copyVisited = element.copyOfVisited();
                        copyVisited.add(endValue);

                        if (endValue == end) {
                            answerVisited = copyVisited;
                            continue;
                        }
                        queue.add(new Element(endValue, sum, copyVisited));
                    }
                }
            }
        }

        StringBuilder answerBuilder = new StringBuilder();
        answerBuilder.append(map[end]).append("\n");
        answerBuilder.append(answerVisited.size()).append("\n");
        answerVisited.forEach(placeNumber -> {
            answerBuilder.append(placeNumber).append(" ");
        });
        System.out.println(answerBuilder);
    }
}

class Element {
    int startValue, costSum;
    ArrayList<Integer> visited;

    public Element(int endPoint, int costSum, ArrayList<Integer> visited) {
        this.startValue = endPoint;
        this.costSum = costSum;
        this.visited = visited;
    }

    public ArrayList<Integer> copyOfVisited() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int number : visited) {
            temp.add(number);
        }
        return temp;
    }
}
