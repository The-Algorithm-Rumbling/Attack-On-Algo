import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, sr, sc, er, ec;
    static char[][] maps;
    static boolean[][][][][][][][] vis = new boolean[2][2][2][2][2][2][50][50];
    static Queue<int[]> q = new LinkedList<>();

    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<M;
    }

    static int bfs() {
        vis[0][0][0][0][0][0][sr][sc] = true;
        q.add(new int[]{0,0,0,0,0,0, sr, sc, 0});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int a = curr[0];  int b = curr[1]; int c = curr[2]; int d = curr[3]; int e = curr[4]; int f = curr[5]; 
            int R = curr[6]; int C = curr[7]; int cnt = curr[8];
            if(maps[R][C] == '1') return cnt;
                

            for(int D=0; D<4; D++) {
                int nr = R+dr[D]; int nc=C+dc[D];
                if(!inRange(nr,nc) || maps[nr][nc] == '#') continue;

                if((maps[nr][nc]=='.' || maps[nr][nc] == '1') && !vis[a][b][c][d][e][f][nr][nc]) {
                    vis[a][b][c][d][e][f][nr][nc] = true;
                    q.add(new int[]{a,b,c,d,e,f,nr,nc,cnt+1});
                }

                int[] nxt = curr.clone();
                nxt[6] = nr; nxt[7] = nc; nxt[8] = cnt+1;
                if(maps[nr][nc] >= 'a' && maps[nr][nc] <= 'f') {
                    int idx = maps[nr][nc] -'a';
                    nxt[idx] = 1;
                    int na = nxt[0];  int nb = nxt[1]; int ncc = nxt[2]; int nd = nxt[3]; int ne =  nxt[4]; int nf = nxt[5]; 
                    if(!vis[na][nb][ncc][nd][ne][nf][nr][nc]) {
                        vis[na][nb][ncc][nd][ne][nf][nr][nc] = true;
                        q.add(new int[]{na, nb, ncc, nd, ne, nf, nr, nc, cnt+1});
                    }
                }

                else if(maps[nr][nc] >= 'A' && maps[nr][nc] <= 'Z') {
                    int idx = maps[nr][nc] -'A';
                    if(curr[idx] == 1 && !vis[a][b][c][d][e][f][nr][nc]) {
                        vis[a][b][c][d][e][f][nr][nc] = true;
                        q.add(new int[]{a,b,c,d,e,f, nr,nc,cnt+1});
                    }
                }
            } 
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new char[N][M];
        for(int r=0; r<N; r++) {
            String s = br.readLine();
            for(int c=0; c<M; c++) {
                maps[r][c] = s.charAt(c);
                if(maps[r][c] == '0') {
                    sr = r; sc = c;
                    maps[r][c] = '.';
                }
            }
        }
        System.out.println(bfs());
    }
}