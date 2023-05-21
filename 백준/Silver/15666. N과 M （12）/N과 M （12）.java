import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] result;
    static HashSet<Integer> numberSet;
    static List<Integer> numberList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numberSet = new HashSet<>();
        result = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberSet.add(Integer.parseInt(st.nextToken()));
        }
        numberList = new ArrayList<>(numberSet);
        Collections.sort(numberList);
        find(0, 0);
        System.out.println(sb);
    }

    public static void find(int start, int cnt) {
        if (cnt == M) {
            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < numberList.size(); i++) {
            result[cnt] = numberList.get(i);
            find(i, cnt + 1);
        }
    }
}