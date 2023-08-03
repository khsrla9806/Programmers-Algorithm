import java.util.*;

class Solution {
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        createMap(rows, columns);
        
        for (int i = 0; i < queries.length; i++) {
            int[] firstCors = {queries[i][0], queries[i][1]};
            int[] secondCors = {queries[i][2], queries[i][3]};
            
            LinkedList<int[]> borderCors = findBorderCors(firstCors, secondCors);
            
            int answerValue = turnBorder(borderCors);
            answer[i] = answerValue;
        }
        
        return answer;
    }
    
    private void createMap(int rows, int columns) {
        map = new int[rows + 1][columns + 1];
        int value = 1;
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                map[r][c] = value++;
            }
        }
    }
    
    /** @returns 테두리 좌표 리스트 반환  */
    private LinkedList<int[]> findBorderCors(int[] firstCors, int[] secondCors) {
        LinkedList<int[]> result = new LinkedList<>();
        
        int curRow = firstCors[0];
        int curCol = firstCors[1];
        result.add(new int[]{curRow, curCol});
        
        // 오른쪽 탐색 (col + 1)
        while (curCol + 1 <= secondCors[1]) {
            curCol++;
            result.add(new int[]{curRow, curCol});
        }
        
        // 아래쪽 탐색 (row + 1)
        while (curRow + 1 <= secondCors[0]) {
            curRow++;
            result.add(new int[]{curRow, curCol});
        }
        
        // 왼쪽 탐색 (col - 1)
        while (curCol - 1 >= firstCors[1]) {
            curCol--;
            result.add(new int[]{curRow, curCol});
        }
        
        // 위쪽 탐색 (row - 1)
        while (curRow - 1 > firstCors[0]) {
            curRow--;
            result.add(new int[]{curRow, curCol});
        }
        
        return result;
    }
    
    /** @returns 테두리 회전 후 변동 값 중에 최소값 반환 */
    private int turnBorder(LinkedList<int[]> borderCors) {
        int[] first = borderCors.pollFirst();
        int[] last = borderCors.peekLast();
        int before = map[first[0]][first[1]];
        int minValue = before;
        
        map[first[0]][first[1]] = map[last[0]][last[1]];
        
        while (!borderCors.isEmpty()) {
            int[] cors = borderCors.poll();
            int currentValue = map[cors[0]][cors[1]];
            map[cors[0]][cors[1]] = before;
            before = currentValue;
            minValue = Math.min(minValue, currentValue);
        }
        
        return minValue;
    }
}