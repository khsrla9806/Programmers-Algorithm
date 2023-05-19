import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 숫자 리스트 만들고, 중복제거된 Set 생성
        TreeSet<Integer> numbers = new TreeSet<>();
        int[] numberList = new int[n];
        int index = 0;
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            numbers.add(number);
            numberList[index++] = number;
        }

        // Set 결과 토대로 Map에 반영
        Map<Integer, Integer> suppressedNum = new HashMap<>();
        int count = 0;
        for (int num : numbers) {
            suppressedNum.put(num, count++);
        }

        // 배열 순회하며 압축 좌표 생성
        StringBuilder sb = new StringBuilder();
        for (int num : numberList) {
            sb.append(suppressedNum.get(num)).append(" ");
        }
        System.out.println(sb);
    }
}
