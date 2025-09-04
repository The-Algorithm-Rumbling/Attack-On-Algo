class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int [2];
        
        for (int i = 1; i <= Math.sqrt(yellow); i++) {
            if (yellow % i == 0) {
                int a = i > yellow/i ? i : yellow/i;
                int b = a == i ? yellow/i : i;
                if ((a+2)*2 + (b)*2 == brown) {
                    answer[0] = a + 2;
                    answer[1] = b + 2;
                    break;
                }
            }
        }
        
        return answer;
    }
}