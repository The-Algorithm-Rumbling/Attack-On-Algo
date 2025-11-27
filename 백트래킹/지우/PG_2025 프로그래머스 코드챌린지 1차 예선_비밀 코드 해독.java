import java.util.*;

class Solution {
    int answer = 0;
    int n; 
    int[][] q;
    int[] ans;
    ArrayList<Integer> picks = new ArrayList<>();
    
    boolean check() {
        for(int i=0; i<q.length; i++) {
            int cnt = 0;
            for(int j=0; j<5; j++) {
                if(picks.contains(q[i][j])) cnt++;
            }
            
            if(cnt != ans[i]) return false;
        }
        
        return true;
    }
    
    void dfs(int cnt, int start) {
        if(cnt == 5) {
            if(check()) answer++;
            return;
        }
        
        for(int i=start; i<=n; i++) {
            picks.add(i);
            dfs(cnt+1, i+1);
            picks.remove(picks.size()-1);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        dfs(0, 1);
        
        return answer;
    }
}