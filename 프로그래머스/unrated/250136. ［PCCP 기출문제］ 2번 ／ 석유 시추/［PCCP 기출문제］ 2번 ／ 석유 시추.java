import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] zone;
    static int zoneId ;
    static Map<Integer, Integer> zoneScore;
    
    public int solution(int[][] land) {
        init(land);
        int answer = 0;
        
        // 석유 위치 탐색
        for (int r = 0; r < land.length; r++) {
            for (int c = 0; c < land[0].length; c++) {
                if (land[r][c] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{r, c});
                    land[r][c] = 0;
                    zone[r][c] = zoneId;
                    bfs(queue, land);
                }
            }
        }
        
        // 최대 뽑을 수 있는 총 석유량 탐색
        for (int c = 0; c < land[0].length; c++) {
            boolean[] flag = new boolean[zoneId];
            int colScore = 0;
            for (int r = 0; r < land.length; r++) {
                int pickedZoneId = zone[r][c];
                if (pickedZoneId != 0 && !flag[pickedZoneId]) {
                    colScore += zoneScore.get(pickedZoneId);
                    flag[pickedZoneId] = true;
                }
            }
            answer = Math.max(answer, colScore);
        }
        
        return answer;
    }
    
    public void init(int[][] land) {
        zone = new int[land.length][land[0].length];
        zoneId = 1;
        zoneScore = new HashMap<Integer, Integer>();
    }
    
    public void bfs(Queue<int[]> queue, int[][] land) {
        int score = 0;
        
        while (!queue.isEmpty()) {
            int[] cors = queue.poll();
            int r = cors[0], c = cors[1];
            score++;
            
            for (int i = 0; i < 4; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];
                
                if (nextR >= 0 && nextR < land.length 
                    && nextC >= 0 && nextC < land[0].length 
                    && land[nextR][nextC] == 1) {
                    queue.add(new int[]{nextR, nextC});
                    land[nextR][nextC] = 0;
                    zone[nextR][nextC] = zoneId;
                }
            }
        }
        
        zoneScore.put(zoneId++, score);
    }
}