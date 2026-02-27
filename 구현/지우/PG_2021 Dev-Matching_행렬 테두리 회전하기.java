class Solution {
    int N; int M;
    int[][] maps;
    
    int dr[] = {1,0,-1,0};
    int dc[] = {0,1,0,-1};
    
    boolean inRange(int r, int c, int sr, int sc, int er, int ec) {
        return r>=sr && r<=er && c>=sc && c<=ec;
    }
    
    int move(int sr, int sc, int er, int ec) {
        int num = maps[sr][sc];
        int min = num;
        
        int r = sr; int c = sc;
        int d=0;
        while(d<4) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(!inRange(nr,nc, sr,sc,er,ec)) {
                d++; 
                nr = r+dr[d]; nc = c+dc[d];
            }
            if(nr==sr && nc==sc) break;
            
            maps[r][c] = maps[nr][nc];
            min = Math.min(min, maps[r][c]);
            r = nr; c = nc;
        }
        
        maps[sr][sc+1] = num;
        return min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        N = rows; M = columns;
        maps = new int[N][M];
        
        int num = 1;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                maps[r][c] = num++;
            }
        }
        
        for(int i=0; i<queries.length; i++) {
            answer[i] = move(queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1);
        }
        
        return answer;
    }
}