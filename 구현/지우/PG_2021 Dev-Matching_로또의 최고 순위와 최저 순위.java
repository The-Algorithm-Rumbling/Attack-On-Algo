import java.util.*;

class Solution {
    int solve(int cnt) {
        if(cnt <= 1) return 6;
        return 7-cnt;
    }
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int zeroCnt = 0;
        HashSet<Integer> sets = new HashSet<>();
        for(int i=0; i<6; i++) {
            if(lottos[i] != 0) {
                sets.add(lottos[i]);
            } else {
                zeroCnt++;
            }
        }
        
        int cnt = 0; int candidate = 0;
        for(int i=0; i<6; i++) {
            int win = win_nums[i];
            if(sets.contains(win)) {
                cnt++;
            } else {
                candidate++;
            }
        }
        answer[1] = solve(cnt);
        
        if(candidate <= zeroCnt) {
            answer[0] = solve(cnt + candidate);
        } else {
            answer[0] = solve(cnt+ zeroCnt);
        }
        
        return answer;
    }
}