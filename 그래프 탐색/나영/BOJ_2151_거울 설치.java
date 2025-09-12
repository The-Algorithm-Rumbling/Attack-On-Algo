import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][][] vis;
    static int n, x, y, ans=Integer.MAX_VALUE;
    static char [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static Queue<int[]> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        map = new char [n][n];
        vis = new int [n][n][4];

        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < n; c++) {
                Arrays.fill(vis[r][c], ans);
                if (map[r][c] == '#') {
                    x = r;
                    y = c;
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            int nr = x + dr[d];
            int nc = y + dc[d];
            vis[x][y][d] = 0;

            if (check(nr, nc) && map[nr][nc] != '*') {
                que.offer(new int [] {nr, nc, d, 0});
                vis[nr][nc][d] = 0;
            }
        }

        bfs();
        
        System.out.print(ans);
    }

    static void bfs () {
        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (map[q[0]][q[1]] == '#') {
                ans = Math.min(ans, q[3]);
                continue;
            }
            
            if (map[q[0]][q[1]] == '.') {
                find(q[0], q[1], q[2], q[3]);
            } else {
                for (int d = 0; d < 4; d++) {
                    if (d == q[2]) find(q[0], q[1], q[2], q[3]);
                    else find(q[0], q[1], d, q[3] + 1);
                }
            }
        }
    }

    static void find (int r, int c, int d, int cnt) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        if (check(nr, nc) && map[nr][nc] != '*' && vis[nr][nc][d] > cnt) {
            vis[nr][nc][d] = cnt;
            que.offer(new int [] {nr, nc, d, cnt});
        }
    }

    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}