import java.util.*;

class Solution {
    int[][] land;
    int N, M;
    Map<Integer, Integer> m = new HashMap<>();
    
    int dr[] = {-1,0,1,0};
    int dc[] = {0,1,0,-1};
    
    boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<M;
    }
    
    void bfs(int sr, int sc, int num) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr,sc});
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0]; int c= curr[1];
            cnt++;
            
            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc=c+dc[d];
                if(inRange(nr,nc) && land[nr][nc] == 1) {
                    land[nr][nc] = num;
                    q.add(new int[]{nr,nc});
                }
            }
        }
        
        m.put(num, cnt);
    }
    
    public int solution(int[][] land) {
        this.land = land;
        N = land.length; M = land[0].length;
        
        int num = 2;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(land[r][c] == 1) {
                    land[r][c] = num;
                    bfs(r,c,num);
                    num++;
                }
            }
        }
        
        int answer = 0;
        for(int c=0; c<M; c++) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            
            for(int r=0; r<N; r++) {
                if(land[r][c] > 1) {
                    if(!set.contains(land[r][c])) {
                        set.add(land[r][c]);
                        sum += m.get(land[r][c]);
                    }
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}