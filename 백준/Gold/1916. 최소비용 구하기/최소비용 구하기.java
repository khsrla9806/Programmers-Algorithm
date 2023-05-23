import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dist;
    static List<City>[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cities = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) cities[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());
            cities[from].add(new City(to, price));
        }

        tokenizer = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int goal = Integer.parseInt(tokenizer.nextToken());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(start);
        System.out.println(dist[goal]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<City> queue = new PriorityQueue<>((c1, c2) -> c1.price - c2.price);
        boolean[] visited = new boolean[N + 1];
        queue.offer(new City(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            City curr = queue.poll();

            if (visited[curr.dest]) continue;
            visited[curr.dest] = true;
            for (City city : cities[curr.dest]) {
                if (dist[city.dest] > dist[curr.dest] + city.price) {
                    dist[city.dest] = dist[curr.dest] + city.price;
                    queue.offer(new City(city.dest, dist[city.dest]));
                }
            }
        }
    }
}

class City {
    int dest;
    int price;

    public City(int dest, int price) {
        this.dest = dest;
        this.price = price;
    }
}