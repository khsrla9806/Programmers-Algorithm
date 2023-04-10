import java.io.*;

public class Main {
    static int n;
    static int[] numbers;
    static int[] operator; // {+, -, *, /}

    static int maxAns = Integer.MIN_VALUE;
    static int minAns = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        numbers = new int[n];
        operator = new int[4]; // {+, -, *, /}

        String[] inputData = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(inputData[i]);
        }

        inputData = reader.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(inputData[i]);
        }

        fullSearch(0, numbers[0], operator[0], operator[1], operator[2], operator[3]);

        System.out.println(maxAns);
        System.out.println(minAns);
    }

    public static void fullSearch(int index, int result, int plusCnt, int minusCnt, int prodCnt, int divideCnt) {
        if (index++ == n - 1) {
            maxAns = Math.max(maxAns, result);
            minAns = Math.min(minAns, result);
            return;
        }

        if (plusCnt != 0) {
            fullSearch(index, result + numbers[index], plusCnt - 1, minusCnt, prodCnt, divideCnt);
        }

        if (minusCnt != 0) {
            fullSearch(index, result - numbers[index], plusCnt, minusCnt - 1, prodCnt, divideCnt);
        }

        if (prodCnt != 0) {
            fullSearch(index, result * numbers[index], plusCnt, minusCnt, prodCnt - 1, divideCnt);
        }

        if (divideCnt != 0) {
            fullSearch(index, result / numbers[index], plusCnt, minusCnt, prodCnt, divideCnt - 1);

        }
    }
}