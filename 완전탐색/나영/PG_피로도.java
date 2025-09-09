class Solution {
    static boolean [] vis;
    static int answer = -1;
    public int solution(int k, int[][] dungeons) {
        
        vis = new boolean [dungeons.length];
        
        dfs(k, 0, dungeons);
        
        return answer;
    }
    
    static void dfs (int h, int cnt, int [][] dungeons) {
        answer = Math.max(answer, cnt);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (vis[i] || h < dungeons[i][0]) continue;
            vis[i] = true;
            dfs(h-dungeons[i][1], cnt+1, dungeons);
            vis[i] = false;
        }
    }
}