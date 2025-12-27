import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;
    static char [][] map;
    static boolean [][][][] vis;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static class P {
        int r, c, k, cnt, am;

        P(int r, int c, int k, int cnt, int am) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
            this.am = am;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        vis = new boolean[n][m][k+1][2];
        Queue<P> que = new LinkedList<>();
        que.offer(new P(0, 0, k, 1, 0));
        vis[0][0][k][0] = true;

        while(!que.isEmpty()) {
            P p = que.poll();

            if (p.r == n-1 && p.c == m-1) return p.cnt;

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d]; 
                int nc = p.c + dc[d];

                if (check(nr, nc)) {
                    if (map[nr][nc] == '1' && p.k > 0) {
                        if (p.am == 0 && !vis[nr][nc][p.k-1][1]) {
                            vis[nr][nc][p.k-1][1] = true;
                            que.offer(new P(nr, nc, p.k-1, p.cnt+1, 1));
                        } else if (p.am == 1 && !vis[p.r][p.c][p.k][0] ) {
                            vis[p.r][p.c][p.k][0] = true;
                            que.offer(new P(p.r, p.c, p.k, p.cnt+1, 0));
                        }
                    } else if (map[nr][nc] == '0' && !vis[nr][nc][p.k][1-p.am]) {
                        vis[nr][nc][p.k][1-p.am] = true;
                        que.offer(new P(nr, nc, p.k, p.cnt+1, 1-p.am));
                    }
                }
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}