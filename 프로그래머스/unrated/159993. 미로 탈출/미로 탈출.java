import java.util.*;

class Solution {
    static boolean[][] flag;
    static final int[][] MOVE = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String[] maps) {
        int[] start = {0, 0};
        int[] lever = {0, 0};
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].contains("S")) {
                start[0] = i;
                start[1] = maps[i].indexOf('S');
            }
            
            if (maps[i].contains("L")) {
                lever[0] = i;
                lever[1] = maps[i].indexOf('L');
            }
        }
        
        // 레버까지 최소 거리
        flag = new boolean[maps.length][maps[0].length()];
        int leverDis = bfs(maps, start[0], start[1], 0, 'L');
        if (leverDis == -1) {
            return -1;
        }
        
        // 출구까지 최소 거리
        flag = new boolean[maps.length][maps[0].length()];
        int exitDis = bfs(maps, lever[0], lever[1], 0, 'E');
        if (exitDis == -1) {
            return -1;
        }
        
        return leverDis + exitDis;
    }
    
    public int bfs(String[] maps, int x, int y, int distance, char target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, distance});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if (maps[current[0]].charAt(current[1]) == target) {
                return current[2];
            }
            
            for (int[] move : MOVE) {
                int nX = current[0] + move[0];
                int nY = current[1] + move[1];
                
                if (nX >= 0 && nX < maps.length 
                    && nY >= 0 && nY < maps[0].length()) {
                    if (flag[nX][nY] 
                        || maps[current[0]].charAt(current[1]) == 'X') {
                        continue;
                    }
                    
                    flag[nX][nY] = true;
                    queue.add(new int[]{nX, nY, current[2] + 1});
                }
            }
        }
        
        return -1;
    }
}