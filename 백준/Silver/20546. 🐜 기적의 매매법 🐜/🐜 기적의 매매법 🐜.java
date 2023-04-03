import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int money = Integer.parseInt(reader.readLine());
        int[] jun = {money, 0};
        int[] sung = {money, 0};
        int[] sungRule = {0, 0}; // {상승 전매도, 하강 전매수}
        int day = 1;
        int todayStockPrice = 0;

        String[] machineDuck = reader.readLine().split(" ");

        while (day <= 14) {
            todayStockPrice = Integer.parseInt(machineDuck[day - 1]);

            // 준현
            if (jun[0] >= todayStockPrice) {
                jun[1] += jun[0] / todayStockPrice;
                jun[0] -= todayStockPrice * (jun[0] / todayStockPrice);
            }

            // 성민
            if (day != 1) {
                int yesterdayStockPrice = Integer.parseInt(machineDuck[day - 2]);
                if (yesterdayStockPrice < todayStockPrice) {
                    sungRule[0]++;
                    sungRule[1] = 0;
                } else if (yesterdayStockPrice > todayStockPrice) {
                    sungRule[1]++;
                    sungRule[0] = 0;
                } else {
                    sungRule[0] = 0;
                    sungRule[1] = 0;
                }
            }

            if (sungRule[0] >= 3 && sung[1] != 0) {
                sung[0] += todayStockPrice * sung[1];
                sung[1] = 0;
            } else if (sungRule[1] >= 3 && (sung[0] >= todayStockPrice)) {
                sung[1] += sung[0] / todayStockPrice;
                sung[0] -= todayStockPrice * (sung[0] / todayStockPrice);
            }

            day++;
        }

        int junTotalAssets = jun[0] + (todayStockPrice * jun[1]);
        int sungTotalAssets = sung[0] + (todayStockPrice * sung[1]);

        if (junTotalAssets > sungTotalAssets) {
            System.out.println("BNP");
        } else if (junTotalAssets < sungTotalAssets) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}