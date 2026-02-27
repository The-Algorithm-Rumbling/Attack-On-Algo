import java.util.*;

class Pos {
    int r; 
    int c;
    
    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    int N, M;
    char[][] maps;
    boolean[][] vis;
    Queue<Pos> q = new LinkedList<>();
    
    int dr[] = {1,0,-1,0};
    int dc[] = {0,1,0,-1};
    
    boolean inRange(int r, int c) {
        return r>=0 && r<N+2 && c>=0 && c<M+2;
    }
    
    void bfs(int sr, int sc, char findC) {
        ArrayList<Pos> kills = new ArrayList<>();
        vis = new boolean[N+2][M+2];
        vis[sr][sc] = true;
        q.add(new Pos(sr,sc));
        
        while(!q.isEmpty()) {
            Pos curr = q.poll();
            int r = curr.r; int c = curr.c;
            
            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c+dc[d];
                
                if(!inRange(nr, nc)) continue;
                if(maps[nr][nc] == '\0' && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.add(new Pos(nr,nc));
                }
                else if(maps[nr][nc] == findC && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    kills.add(new Pos(nr,nc));
                }
            }
        }
        
        for(Pos p : kills) {
            maps[p.r][p.c] = '\0';
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length; M = storage[0].length();
        maps = new char[N+2][M+2];
        
        for(int r=1; r<=N; r++) {
            String s = storage[r-1];
            for(int c=1; c<=M; c++) {
                char ch = s.charAt(c-1);
                maps[r][c] = ch;
            }
        }
        
        for(int i=0; i<requests.length; i++) {
            String s = requests[i];
            char ch = s.charAt(0);
            
            if(s.length() == 1) {
                bfs(0,0,ch);
            }
            else {
                
                for(int r=1; r<=N; r++) {
                    for(int c=1; c<=M; c++) {
                        if(maps[r][c] == ch) {
                            maps[r][c] = '\0';
                        }
                    }
                }
                
            }
        }
        
        int answer = 0;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(maps[r][c] != '\0') {
                    answer++;
                }
            }
        }
        return answer;
    }
}