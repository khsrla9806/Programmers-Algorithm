import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static LinkedList<Long> numbers;
    static LinkedList<Character> operators;
    static char[][] operatorPriorities = {
        {'+', '-', '*'},
        {'+', '*', '-'},
        {'-', '+', '*'},
        {'-', '*', '+'},
        {'*', '+', '-'},
        {'*', '-', '+'}
    };
        
    public long solution(String expression) {
        long answer = 0;
        
        seperatorNumbers(expression);
        seperateOperators(expression);
        
        for (char[] operatorPriority : operatorPriorities) {
            LinkedList<Long> tempNumbers = new LinkedList<>(numbers);
            LinkedList<Character> tempOperator = new LinkedList<>(operators);
            
            for (char oper : operatorPriority) {
                while (!tempOperator.isEmpty()) {
                    int index = tempOperator.indexOf(oper);
                    if (index == -1) {
                        break;
                    }
                    
                    char operator = tempOperator.remove(index);
                    long firstNumber = tempNumbers.remove(index);
                    long secondNumber = tempNumbers.remove(index);
                    
                    if (operator == '+') {
                        tempNumbers.add(index, firstNumber + secondNumber);
                    } else if (operator == '-') {
                        tempNumbers.add(index, firstNumber - secondNumber);
                    } else if (operator == '*') {
                        tempNumbers.add(index, firstNumber * secondNumber);
                    }
                }
            }
            answer = Math.max(Math.abs(tempNumbers.poll()), answer);
        }
        
        return answer;
    }
    
    /* 숫자 분리 */
    public void seperatorNumbers(String expression) {
        String[] temp = expression.split("\\*|\\+|\\-");
        
        List<Long> numbers = Arrays.stream(temp)
            .map(Long::valueOf)
            .collect(Collectors.toList());
        
        this.numbers = new LinkedList<>(numbers);
    }
    
    /* 연산자 분리 */
    public void seperateOperators(String expression) {
        operators = new LinkedList<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '+'|| ch == '*' || ch == '-') {
                operators.add(ch);
            }
        }
    }
}