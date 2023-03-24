import java.util.*;

class Solution {
    static final int[][] MOVE = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n, m;
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        boolean[][] visit = new boolean[n][m];
        int[] start = {0, 0};
        // 시작 지점 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        
        return bfs(start[0], start[1], visit, board);
    }
    
    public int bfs(int x, int y, boolean[][] visit, String[] board) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (visit[cur[0]][cur[1]]) {
                continue;
            }
            visit[cur[0]][cur[1]] = true;
            
            for (int[] move : MOVE) {
                int nX = cur[0];
                int nY = cur[1];
                
                while (true) {
                    nX += move[0];
                    nY += move[1];
                    
                    if ((nX < 0 || nX >= n || nY < 0 || nY >= m) || board[nX].charAt(nY) == 'D') {
                        nX -= move[0];
                        nY -= move[1];
                        break;
                    }
                }
                
                if (nX == cur[0] && nY == cur[1]) {
                    continue;
                }
                
                if (board[nX].charAt(nY) == 'G') {
                    return cur[2] + 1;
                }
                
                q.add(new int[]{nX, nY, cur[2] + 1});
            }
        }
        
        return -1;
    }
}