import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        ArrayList<String[]> totalCase = new ArrayList<>();

        // 데이터 입력 처리
        for (int test = 0; test < testCase; test++) {
            int telCount = Integer.parseInt(reader.readLine());
            String[] tels = new String[telCount];

            for (int count = 0; count < telCount; count++) {
                String telNumber = reader.readLine();
                tels[count] = telNumber;
            }

            Arrays.sort(tels, (s1, s2) -> s1.length() - s2.length());
            totalCase.add(tels);
        }

        // 결과 확인
        for (String[] pickedCase : totalCase) {
            boolean isValid = true;
            Map<String, Boolean> telHistory = new HashMap<>();

            for (String tel : pickedCase) {
                if (!telHistory.isEmpty()) {
                    for (int index = 1; index <= tel.length(); index++) {
                        String subTel = tel.substring(0, index);

                        if (telHistory.containsKey(subTel)) {
                            isValid = false;
                            break;
                        }
                    }
                }

                if (!isValid) break;
                telHistory.put(tel, true);
            }
            System.out.println(isValid ? "YES" : "NO");
        }
    }
}
