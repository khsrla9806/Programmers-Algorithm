import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String n = reader.readLine();
        int targetNum = Integer.parseInt(n);
        int m = Integer.parseInt(reader.readLine());

        // 고장난 버튼 확인
        boolean[] buttons = new boolean[10];
        Arrays.fill(buttons, true);
        if (m != 0) {
            for (String button : reader.readLine().split(" ")) {
                buttons[Integer.parseInt(button)] = false; // 망가진 버튼은 false
            }
        }

        // 직접 해당 채널을 입력하는 경우 => 최대한 근접하게 채널을 골라야한다. (고장나지 않은 버튼들을 이용해서)
        int answer = Math.abs(targetNum - 100);
        int upVal = targetNum;
        int upResult = 0;
        while (upVal < 1000000) {
            if (isMatch(upVal, buttons)) {
                upResult += String.valueOf(upVal).length();
                answer = Math.min(answer, upResult);
                break;
            }
            upResult++;
            upVal++;
        }

        int downVal = targetNum;
        int downResult = 0;
        while (downVal >= 0) {
            if (isMatch(downVal, buttons)) {
                downResult += String.valueOf(downVal).length();
                answer = Math.min(answer, downResult);
                break;
            }
            downResult++;
            downVal--;
        }

        System.out.println(answer);
    }

    public static boolean isMatch(int value, boolean[] buttons) {
        char[] arr = String.valueOf(value).toCharArray();
        for (char val : arr) {
            if (!buttons[val - '0']) {
                return false;
            }
        }
        return true;
    }
}