import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, hx, hy, ex, ey;
    static int [][] map;
    static int [][][] vis;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int [n][m];
        vis = new int [n][m][2];
        
        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken())-1;
        hy = Integer.parseInt(st.nextToken())-1;
        
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                Arrays.fill(vis[r][c], Integer.MAX_VALUE);
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs(hx, hy));
        
    }

    static int bfs(int r, int c) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int [] {r, c, 1});
        vis[r][c][1] = 0;

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == ex && q[1] == ey) return vis[ex][ey][q[2]];

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                int next = vis[q[0]][q[1]][q[2]] + 1;
    
                if (check(nr, nc)) {
                    if (map[nr][nc] != 0 && q[2] == 1 && vis[nr][nc][0] > next) {
                        vis[nr][nc][0] = next;
                        que.offer(new int [] {nr, nc, 0});
                    }

                    if (map[nr][nc] == 0 && vis[nr][nc][q[2]] > next) {
                        vis[nr][nc][q[2]] = next;
                        que.offer(new int [] {nr, nc, q[2]});
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