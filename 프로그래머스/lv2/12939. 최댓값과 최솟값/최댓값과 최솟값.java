class Solution {
    public String solution(String s) {
        String[] datas = s.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (String data : datas) {
            int intOfData = Integer.parseInt(data);
            max = Math.max(max, intOfData);
            min = Math.min(min, intOfData);
        }
        return min + " " + max;
    }
}