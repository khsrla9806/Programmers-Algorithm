class Solution {
    public String solution(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        
        boolean isFirst = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                isFirst = true;
            } else if (isFirst) {
                chars[i] = Character.toUpperCase(chars[i]);
                isFirst = false;
            }
        }
        
        return new String(chars);
    }
}