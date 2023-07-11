class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        
        boolean isFirst = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                isFirst = true;
            } else if (isFirst) {
                chars[i] = Character.toUpperCase(chars[i]);
                isFirst = false;
            } else if (!isFirst) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        
        return new String(chars);
    }
}