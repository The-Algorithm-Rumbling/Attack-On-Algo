import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] map, remove;
    static boolean [][] vis;
    static int n, m, days, cheese;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) cheese++;
            }
        }

        if (cheese == 0) {
            System.out.print(0);
            return;
        }

        while (cheese > 0) {
            bfs();
            reMap();
            days++;
        }

        System.out.print(days);
    }

    static void bfs () {
        Queue<int []> que = new LinkedList<>();
        vis = new boolean [n][m];
        remove = new int [n][m];
        vis[0][0] = true;
        que.offer(new int [] {0, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && !vis[nr][nc]) {
                    if (map[nr][nc] == 0) {
                        vis[nr][nc] = true;
                        que.offer(new int [] {nr, nc});
                    } else remove[nr][nc]++;
                }
            }
        }
    }

    static void reMap () {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (remove[r][c] >= 2) {
                    cheese--;
                    map[r][c] = 0;
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}