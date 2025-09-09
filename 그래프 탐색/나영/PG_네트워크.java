import java.util.List;
import java.util.ArrayList;

class Solution {
    static List<Integer> list [];
    static boolean [] vis;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        list = new ArrayList [n];
        vis = new boolean [n];
        
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != i && computers[i][j] == 1) {
                    list[i].add(j);
                    list[j].add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                answer++;
                vis[i] = true;
                dfs(i);
            }
        }
        
        return answer;
    }
    
    static void dfs(int node) {
        for (int i : list[node]) {
            if (!vis[i]) {
                vis[i] = true;
                dfs(i);
            }
        }
    }
}