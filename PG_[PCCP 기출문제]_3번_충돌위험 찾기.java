import java.util.*;

class Pos {
    int r;
    int c;
    
    Pos(int r,int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    Map<Integer, Pos> dPos = new HashMap<>();
    Queue<int[]> q = new LinkedList<>();
    int[][] routes;
    int[][] maps;
    int INF = 98765432;
    
    int dr[] = {1,-1,0,0};
    int dc[] = {0,0,1,-1};
    
    boolean inRange(int r, int c) {
        return r>=1 && r<=100 && c>=1 && c<=100;
    }
    
    int distance(int r, int c, int a, int b) {
        return Math.abs(r-a) + Math.abs(c-b);
    }
    
    int bfs() {
        int size = q.size();
        int cnt = 0;
        while(size-- > 0) {
            int[] curr = q.poll();
            int r = curr[0]; int c = curr[1]; int num = curr[2];
            int endIdx = curr[3];
            
            int end = routes[num-1][endIdx]; 
            int er = dPos.get(end).r; int ec = dPos.get(end).c;
            
            int minDis = INF; 
            int minD = 0;
            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c+dc[d]; 
                if(!inRange(nr,nc)) continue;
                int dis = distance(nr,nc,er,ec);
                
                if(minDis > dis) {
                    minDis = dis;
                    minD = d;
                }
            }
            
            int nr = r+dr[minD]; int nc = c+dc[minD];
            if(nr == er && nc == ec) {
                if(routes[num-1].length > endIdx+1) { // 다음 목적지 존재
                    q.add(new int[]{nr,nc,num, endIdx+1});
                }
            } else {
                q.add(new int[]{nr,nc,num, endIdx});
            }
            maps[nr][nc]++;
        }
        
        for(int r=1; r<=100; r++) {
            for(int c=1; c<=100; c++) {
                if(maps[r][c] > 1) cnt++;
            }
        }
        return cnt;
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        this.routes = routes;
        maps = new int[101][101];
        for(int i=1; i<=points.length; i++) {
            dPos.put(i, new Pos(points[i-1][0], points[i-1][1])); // dPos[지점 번호] = r,c
        }
        for(int i=1; i<=routes.length; i++) {
            int start = routes[i-1][0]; 
            Pos p = dPos.get(start);
            
            q.add(new int[]{p.r, p.c, i, 1}); 
            maps[p.r][p.c]++;
        }
        
        for(int r=1; r<=100; r++) {
            for(int c=1; c<=100; c++) {
                if(maps[r][c] > 1) answer++;
            }
        }
        
        while(!q.isEmpty()) {
            maps = new int[101][101];
            answer += bfs();
        }
        
        return answer;
    }
}