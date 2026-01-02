import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[][] maps;
    static boolean[][][][] vis;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dr = {0, 1, 0, -1, 0};
    static int[] dc = {0, 0, 1, 0, -1};
    
    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<M;
    }
    
    static int bfs() {
        vis[0][K][0][0] = true;
        q.add(new int[]{0,K,0,0, 1});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int day = curr[0]; int crush = curr[1]; 
            int r = curr[2]; int c = curr[3]; 
            int cnt = curr[4];

            if(r == N-1 && c==M-1) return cnt;

            for(int d=0; d<5; d++) {
                int nr = r+dr[d]; int nc = c+dc[d]; int nday = (day+1)%2;
                if(!inRange(nr,nc)) continue;

                if(d==0 && day==1 && maps[nr][nc]==1 && crush>=1 && !vis[nday][crush][nr][nc]) {
                    vis[nday][crush][nr][nc] = true; 
                    q.add(new int[]{nday, crush, nr, nc, cnt+1});   
                }
                else if(maps[nr][nc]==0 && !vis[nday][crush][nr][nc]) {
                    vis[nday][crush][nr][nc] = true;
                    q.add(new int[]{nday, crush, nr, nc, cnt+1});
                }
                else if(day==0 && crush>=1 && maps[nr][nc]==1 && !vis[nday][crush-1][nr][nc]) {
                    vis[nday][crush-1][nr][nc] = true;
                    q.add(new int[]{nday, crush-1, nr, nc, cnt+1});
                }
                
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        vis = new boolean[2][K+1][N][M];

        for(int r=0; r<N; r++) {
            String s = br.readLine();
            for(int c=0; c<M; c++) {
                maps[r][c] = s.charAt(c)-'0';
            }
        }

        System.out.println(bfs());
        
    }
}