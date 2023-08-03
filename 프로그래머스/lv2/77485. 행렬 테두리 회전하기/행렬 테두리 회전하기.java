import java.util.*;

class Solution {
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        createMap(rows, columns);
        
        for (int i = 0; i < queries.length; i++) {
            int[] firstCors = {queries[i][0], queries[i][1]};
            int[] secondCors = {queries[i][2], queries[i][3]};
            
            List<int[]> borderCors = findBorderCors(firstCors, secondCors);
            
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
    private List<int[]> findBorderCors(int[] firstCors, int[] secondCors) {
        List<int[]> result = new ArrayList<>();
        
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
    private int turnBorder(List<int[]> borderCors) {
        int[] firstCors = borderCors.get(0);
        int[] lastCors = borderCors.get(borderCors.size() - 1); 
        int before = map[firstCors[0]][firstCors[1]];
        map[firstCors[0]][firstCors[1]] = map[lastCors[0]][lastCors[1]];
        int minValue = before;
        
        for (int i = 1; i < borderCors.size(); i++) {
            int[] curCors = borderCors.get(i);
            int currentValue = map[curCors[0]][curCors[1]];
            minValue = Math.min(minValue, currentValue);
            map[curCors[0]][curCors[1]] = before;
            before = currentValue;
        }
        
        return minValue;
    }
}