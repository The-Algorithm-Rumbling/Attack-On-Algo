import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class P {
        int r, c, d;

        P (int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans;
    static int [][] map;
                    //  상  좌 하 우
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static boolean [][][] vis;
    static Queue<P> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int [n][m];
        vis = new boolean [n][m][4];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 9) {
                    for (int d = 0; d < 4; d++) {
                        vis[r][c][d] = true;
                        que.offer(new P(r, c, d));
                    }
                }
            }
        }

        find();
        
        System.out.println(ans);
    }

    static void find () {
        // ans 중복 방지
        boolean[][] counted = new boolean[n][m];

        while(!que.isEmpty()) {
            P p = que.poll();
            int r = p.r;
            int c = p.c;
            int d = p.d;

            if (!counted[r][c]) {
                ans++;
                counted[r][c] = true;
            }

            if (map[r][c] == 1) {
                if (d % 2 != 0) continue;
            } else if (map[r][c] == 2) {
                if (d % 2 == 0) continue;
            } else if (map[r][c] == 3) {
                if (d == 0) d = 3;
                else if (d == 1) d = 2;
                else if (d == 2) d = 1;
                else d = 0;
            } else if (map[r][c] == 4) {
                if (d == 0) d = 1;
                else if (d == 1) d = 0;
                else if (d == 2) d = 3;
                else d = 2;
            } 
            
            if (map[p.r][p.c] == 0) {
                map[p.r][p.c] = 5;
            }
            
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (check(nr, nc) && !vis[nr][nc][d]) {
                vis[nr][nc][d] = true;
                que.offer(new P (nr, nc, d));
            }
        }
    }

    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}