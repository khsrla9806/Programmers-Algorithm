import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECT = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1 ,-1}};
    static int N, M, K;
    static List<Fireball> fireballList;
    static Queue<Fireball>[][] fireballInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireballInfo = new Queue[N + 1][N + 1];
        fireballList = new ArrayList<>();
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                fireballInfo[row][col] = new LinkedList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Fireball fireball = new Fireball(r, c, m, s, d);
            fireballList.add(fireball);
        }
        int answer = start();
        System.out.println(answer);
    }

    public static int start() {
        // K번의 명령
        while (K-- > 0) {
            move();

            // 나뉠 수 있는 파이어볼 확인
            checkFireballs();
        }

        return getTotalFireballMass();
    }

    public static void move() {
        for (Fireball fireball : fireballList) {
            int[] direct = fireball.getDirectWithSpeed();
            int nextR = fireball.row + direct[0];
            int nextC = fireball.col + direct[1];

            if (!isValid(nextR, nextC)) {
                nextR = resetCors(nextR);
                nextC = resetCors(nextC);
            }

            fireball.setNewRowAndCol(nextR, nextC);
            fireballInfo[nextR][nextC].add(fireball);
        }
    }

    public static int resetCors(int cors) {
        if (cors > N) return cors - N;
        else if (cors < 1) return cors + N;
        return cors;
    }

    public static boolean isValid(int row, int col) {
        return row >= 1 && col >= 1 && row <= N && col <= N;
    }

    public static void checkFireballs() {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (fireballInfo[row][col].size() >= 2) {
                    splitFireballs(fireballInfo[row][col], row, col);
                } else {
                    fireballInfo[row][col].clear();
                }
            }
        }
    }

    public static void splitFireballs(Queue<Fireball> fireballs, int row, int col) {
        int totalMass = 0;
        int totalSpeed = 0;
        int fireballCount = fireballs.size();
        boolean isAllOddOrEven = isAllOddOrEven(fireballs);
        while (!fireballs.isEmpty()) {
            Fireball fireball = fireballs.poll();
            totalMass += fireball.mass;
            totalSpeed += fireball.speed;
            fireballList.remove(fireball);
        }
        int splitFireballMass = totalMass / 5;
        if (splitFireballMass == 0) {
            return; // 소멸
        }
        int splitFireballSpeed = totalSpeed / fireballCount;
        if (isAllOddOrEven) {
            for (int d = 0; d < 8; d += 2) {
                fireballList.add(new Fireball(row, col, splitFireballMass, splitFireballSpeed, d));
            }
        } else {
            for (int d = 1; d < 8; d += 2) {
                fireballList.add(new Fireball(row, col, splitFireballMass, splitFireballSpeed, d));
            }
        }
    }

    public static boolean isAllOddOrEven(Queue<Fireball> fireballs) {
        boolean AllOdd = true;
        boolean AllEven = true;
        for (Fireball fireball : fireballs) {
            if (fireball.direct % 2 == 0) {
                AllOdd = false;
            }
            if (fireball.direct % 2 != 0) {
                AllEven = false;
            }
        }
        return AllOdd || AllEven;
    }

    public static int getTotalFireballMass() {
        int totalMass = 0;
        for (Fireball fireball : fireballList) {
            totalMass += fireball.mass;
        }

        return totalMass;
    }

    static class Fireball {
        int row, col, mass, speed, direct;

        public Fireball(int r, int c, int m, int s, int d) {
            this.row = r;
            this.col = c;
            this.mass = m;
            this.speed = s;
            this.direct = d;
        }

        public int[] getDirectWithSpeed() {
            return new int[]{DIRECT[direct][0] * (speed % N), DIRECT[direct][1] * (speed % N)};
        }

        public void setNewRowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}