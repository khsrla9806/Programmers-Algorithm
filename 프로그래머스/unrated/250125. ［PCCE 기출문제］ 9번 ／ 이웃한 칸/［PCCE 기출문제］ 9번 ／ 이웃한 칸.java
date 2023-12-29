class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        // 상, 하, 좌, 우
        String pickedColor = board[h][w];
        int[] dh = {-1, 1, 0, 0};
        int[] dw = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nextH = h + dh[i];
            int nextW = w + dw[i];
            if (nextH >= 0 && nextH < board.length 
                && nextW >= 0 && nextW < board[0].length) {
                answer += pickedColor.equals(board[nextH][nextW]) ? 1 : 0;
            }
        }
        
        return answer;
    }
}