import java.util.*;

class Solution {
    static List<Integer> list [];
    static boolean [] vis;
    static int x, y, sum;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        list = new ArrayList [n+1];
        
        for (int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int [] arr : wires) {
            list[arr[0]].add(arr[1]);
            list[arr[1]].add(arr[0]);
        }
        
        for (int [] arr : wires) {
            x = arr[0];
            y = arr[1];
            vis = new boolean [n+1];
            sum = 0;
            
            vis[1] = true;
            dfs(1);
            
            answer = Math.min(answer, Math.abs(n - 2*sum));
        }
        
        return answer;
    }
    
    static void dfs(int num) {
        sum++;
        for (int i : list[num]) {
            if (vis[i] || (i == x && num == y) || (num == x && i == y)) continue;
            vis[i] = true;
            dfs(i);
        }
    }
}