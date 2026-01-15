import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        boolean[] used = new boolean[reserve.length];
        boolean[] fixed = new boolean[lost.length];
        int answer = n - lost.length;
        
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (!used[j] && lost[i] == reserve[j]) {
                    used[j] = true;
                    fixed[i] = true;
                    answer++;
                    break;
                }
            }
        }
        for (int i = 0; i < lost.length; i++) {
            if (fixed[i]) continue;

            for (int j = 0; j < reserve.length; j++) {
                if (used[j]) continue;

                if (reserve[j] == lost[i] - 1 || reserve[j] == lost[i] + 1) {
                    used[j] = true;
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}