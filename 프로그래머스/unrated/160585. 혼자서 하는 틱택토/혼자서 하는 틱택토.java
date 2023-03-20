class Solution {
    public int solution(String[] board) {
        int oCnt = 0;
        int xCnt = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    oCnt++;
                } else if (board[i].charAt(j) == 'X') {
                    xCnt++;
                }
            }
        }
        
        // 후공이 선공보다 먼저 나온경우
        if (xCnt > oCnt) {
            return 0;
        }
        
        // 순서대로 하나씩 증가하지 않은 경우
        if (oCnt - xCnt > 1) {
            return 0;
        }
        
        // 게임이 끝났는데 계속 진행된 경우
        String winner = getWinner(board);
        if (winner.equals("Error")) {
            return 0;
        }
        if (winner.equals("oWin")) {
            if (oCnt <= xCnt) {
                return 0;
            }
        }
        if (winner.equals("xWin")) {
            if (oCnt != xCnt) {
                return 0;
            }
        }
        
        return 1;
    }
    
    public String getWinner(String[] board) {
        boolean oWin = false;
        boolean xWin = false;
        for (int i = 0; i < 3; i++) {
            // 가로로 일치하는 경우
            if (board[i].equals("OOO")) {
                oWin = true;
            }
            // 세로로 일치하는 경우
            if (board[0].charAt(i) == 'O'
                && board[0].charAt(i) == board[1].charAt(i)
                && board[1].charAt(i) == board[2].charAt(i)) {
                oWin = true;
            }
        }
        
        if (board[0].charAt(0) == 'O' 
            && board[0].charAt(0) == board[1].charAt(1)
            && board[1].charAt(1) == board[2].charAt(2)) {
            oWin = true;
        }
        
        if (board[0].charAt(2) == 'O'
            && board[0].charAt(2) == board[1].charAt(1)
            && board[1].charAt(1) == board[2].charAt(0)) {
            oWin = true;
        }
        
        for (int i = 0; i < 3; i++) {
            // 가로로 일치하는 경우
            if (board[i].equals("XXX")) {
                xWin = true;
            }
            // 세로로 일치하는 경우
            if (board[0].charAt(i) == 'X'
                && board[0].charAt(i) == board[1].charAt(i)
                && board[1].charAt(i) == board[2].charAt(i)) {
                xWin = true;
            }
        }
        
        if (board[0].charAt(0) == 'X' 
            && board[0].charAt(0) == board[1].charAt(1)
            && board[1].charAt(1) == board[2].charAt(2)) {
            xWin = true;
        }
        
        if (board[0].charAt(2) == 'X'
            && board[0].charAt(2) == board[1].charAt(1)
            && board[1].charAt(1) == board[2].charAt(0)) {
            xWin = true;
        }
        
        if (oWin && xWin) {
            return "Error";
        } else if (oWin) {
            return "oWin";
        } else if (xWin) {
            return "xWin";
        }
        return "noWin";
    }
}