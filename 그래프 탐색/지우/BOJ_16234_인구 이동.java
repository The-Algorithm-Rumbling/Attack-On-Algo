import java.util.*;
import java.io.*;

class Pos {
    int r, c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Main {
    static int N, L, R;
    static int[][] maps;
    static boolean[][] vis;
    static boolean isS;
    static Queue<Pos> q = new LinkedList<>();

    static int dr[] = {1,0,-1,0};
    static int dc[] = {0,1,0,-1};

    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }
    
    static boolean check(int r,int c, int a, int b) {
        int diff = Math.abs(maps[r][c] - maps[a][b]);
        return diff >= L && diff <=R;
    }

    static void bfs(int sr, int sc) {
        vis[sr][sc] = true;
        q.add(new Pos(sr,sc));
        int sum = 0;
        ArrayList<Pos> tmps = new ArrayList<>();

        while(!q.isEmpty()) {
            Pos curr = q.poll();
            int r = curr.r; int c = curr.c;
            sum += maps[r][c];
            tmps.add(new Pos(r,c));

            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c +dc[d];
                if(inRange(nr,nc) && !vis[nr][nc] && check(r,c,nr,nc)) {
                    vis[nr][nc] = true;
                    q.add(new Pos(nr,nc));
                }
            }
        }

        if(tmps.size() > 1) {
            isS = true;
            int num = sum/tmps.size();

            for(Pos t : tmps) {
                maps[t.r][t.c] = num;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        maps = new int[N][N];
        vis = new boolean[N][N];

        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                maps[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int dayCnt = 0;
        while(true) {
            isS = false;
            vis = new boolean[N][N];
            
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(!vis[r][c]) {
                        bfs(r,c);
                    }
                }
            }

            if(isS) {
                dayCnt++;
            } else {
                break;
            }
        }

        System.out.println(dayCnt);
        
    }
}