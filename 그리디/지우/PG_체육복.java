import java.util.*;
class Solution {
    boolean[] rVis;
    int n;
    int[] lost;
    int[] reserve;
    int answer = 0;
    
    void dfs(int sum, int idx) {
        answer = Math.max(answer, sum);
        if(idx == lost.length) {
            return;
        }
        
        int num = lost[idx];
        for(int r=0; r<reserve.length; r++) {
            if(rVis[r]) continue;
            if(reserve[r] == num-1 || reserve[r] == num+1) {
                rVis[r] = true;
                dfs(sum+1, idx+1);
                rVis[r] = false;
            }
        }
        dfs(sum, idx+1);
        
    }
    
    public int solution(int n, int[] lost, int[] reserve) {
        this.n = n;
        
        this.reserve = reserve;
        rVis = new boolean[reserve.length];
        
        HashSet<Integer> lostSets = new HashSet<>();
        for(int i=0; i<lost.length; i++) {
            lostSets.add(lost[i]);
        }
        for(int i=0; i<reserve.length; i++) {
            if(lostSets.contains(reserve[i])) {
                rVis[i] = true;
                lostSets.remove(reserve[i]);
            }
        }
        lost = new int[lostSets.size()];
        int idx = 0;
        for(int s : lostSets) {
            lost[idx] = s;
            idx++;
        }
        
        this.lost = lost;
        int curr = n - lostSets.size();
        
        dfs(curr, 0);
        
        return answer;
    }
}