import java.util.*;

class Solution {
    static long firstSum, secondSum, totalSum, targetSum, maxCount;
    static LinkedList<Integer> firstQueue, secondQueue;
    
    public int solution(int[] queue1, int[] queue2) {
        setInitialValues(queue1, queue2);
        
        if (totalSum % 2 != 0) {
            return -1;
        }
        
        int count = 0;
        while (count <= 600000) {
            if (firstSum == targetSum || secondSum == targetSum) {
                return count;
            }
            
            if (firstSum > secondSum) {
                int value = firstQueue.pollFirst();
                firstSum -= value;
                
                secondQueue.add(value);
                secondSum += value;
            } else {
                int value = secondQueue.pollFirst();
                secondSum -= value;
                
                firstQueue.add(value);
                firstSum += value;
            }
            count++;
        }
        
        return -1;
    }
    
    
    /** 초기 데이터를 세팅 */
    private void setInitialValues(int[] queue1, int[] queue2) {
        firstQueue = new LinkedList<Integer>();
        secondQueue = new LinkedList<Integer>();
        
        for (int num : queue1) {
            firstSum += num;
            firstQueue.add(num);
        }
        
        for (int num : queue2) {
            secondSum += num;
            secondQueue.add(num);
        }
        
        totalSum = firstSum + secondSum;
        targetSum = totalSum / 2;
        maxCount = queue1.length + queue2.length;
    }
}