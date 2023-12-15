import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        PriorityQueue<Value> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            queue.add(new Value(reader.readLine()));
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append(queue.poll()).append("\n");
        }
        System.out.println(builder);
    }
}

class Value implements Comparable<Value> {
    String value;
    ArrayList<String> seperatedValue;

    public Value(String value) {
        this.value = value;
        setSeperatedValue(value);
    }

    private void setSeperatedValue(String value) {
        this.seperatedValue = new ArrayList<>();
        StringBuilder tempBuilder = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char temp = value.charAt(i);

            if (Character.isDigit(temp)) {
                tempBuilder.append(temp);
                continue;
            } else if (tempBuilder.length() > 0) { // 숫자 아니고, 빌더에 값이 있는 경우
                seperatedValue.add(tempBuilder.toString());
                tempBuilder = new StringBuilder();
            }
            seperatedValue.add(String.valueOf(temp));
        }

        if (tempBuilder.length() > 0) { // 다 돌고 빌더에 남아 있는 것이 있으면 마저 넣어줌
            seperatedValue.add(tempBuilder.toString());
        }
    }

    @Override
    public int compareTo(Value other) {
        ArrayList<String> seperatedValueOfOther = other.seperatedValue;
        int smallSize = Math.min(seperatedValueOfOther.size(), this.seperatedValue.size());
        for (int i = 0; i < smallSize; i++) {
            String valueOfThis = this.seperatedValue.get(i);
            String valueOfOther = seperatedValueOfOther.get(i);

            boolean isNumberOfThis = isNumber(valueOfThis);
            boolean isNumberOfOther = isNumber(valueOfOther);

            // 둘 다 숫자인 경우
            if (isNumberOfThis && isNumberOfOther) {
                BigDecimal decimalOfThis = convertToDecimal(valueOfThis);
                BigDecimal decimalOfOther = convertToDecimal(valueOfOther);

                if (decimalOfThis.equals(decimalOfOther)) {
                    // 값도 같고, 0의 개수도 같은 경우
                    if (valueOfThis.length() == valueOfOther.length()) {
                        continue;
                    }
                    return valueOfThis.length() - valueOfOther.length();
                }
                return decimalOfThis.compareTo(decimalOfOther);
            }

            // 둘 중 하나만 숫자인 경우
            if (isNumberOfThis || isNumberOfOther) {
                return isNumberOfThis ? -1 : 1;
            }

            // 둘 다 문자인 경우
            int comparedValue = compare(valueOfThis, valueOfOther);

            if (comparedValue == 0) {
                continue;
            }

            return comparedValue;
        }

        return this.value.length() - other.value.length();
    }

    private int compare(String value1, String value2) {
        if (value1.equals(value2)) {
            return 0;
        }
        if (value1.equalsIgnoreCase(value2)) {
            if (value1.toUpperCase().compareTo(value1) == 0) {
                return -1;
            }
            return 1;
        }

        return value1.compareToIgnoreCase(value2);
    }

    private boolean isNumber(String value) {
        return Character.isDigit(value.charAt(0));
    }

    private BigDecimal convertToDecimal(String value) {
        return new BigDecimal(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
