import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        Country[] countries = new Country[N + 1];
        PriorityQueue<Country> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            int number = Integer.parseInt(tokenizer.nextToken());
            int gold = Integer.parseInt(tokenizer.nextToken());
            int silver = Integer.parseInt(tokenizer.nextToken());
            int bronze = Integer.parseInt(tokenizer.nextToken());
            countries[number] = new Country(number, gold, silver, bronze);
            queue.add(countries[number]);
        }

        ArrayList<Country> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            Country country = queue.poll();

            if (temp.isEmpty()) {
                country.rank = 1;
                temp.add(country);
                continue;
            }

            Country beforeCountry = temp.get(temp.size() - 1);
            if (beforeCountry.compareTo(country) == 0) {
                country.rank = beforeCountry.rank;
            } else {
                country.rank = temp.size() + 1;
            }
            temp.add(country);
        }

        System.out.println(countries[K].rank);
    }
}

class Country implements Comparable<Country> {
    int number;
    int rank;
    Medals medals;

    public Country(int number, int gold, int silver, int bronze) {
        this.number = number;
        this.medals = new Medals(gold, silver, bronze);
    }

    @Override
    public int compareTo(Country country) {
        if (this.medals.gold != country.medals.gold) {
            return country.medals.gold - this.medals.gold;
        }
        if (this.medals.silver != country.medals.silver) {
            return country.medals.silver - this.medals.silver;
        }
        return country.medals.bronze - this.medals.bronze;
    }
}

class Medals {
    int gold;
    int silver;
    int bronze;

    public Medals(int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    public boolean isEqualsReward(Medals medals) {
        return this.gold == medals.gold
                && this.silver == medals.silver
                && this.bronze == medals.bronze;
    }
}