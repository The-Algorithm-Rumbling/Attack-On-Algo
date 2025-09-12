import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char [][] map;
    static int [][][] vis;
    static int n, m, x, y, ans=Integer.MAX_VALUE;
    static PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[3], b[3]));
                    //  상 우 하 좌
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char [m][];
        vis = new int [m][n][4];

        for (int r = 0; r < m; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < n; c++) {
                Arrays.fill(vis[r][c], ans);
                if (map[r][c] == 'C') {
                    x = r;
                    y = c;
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            vis[x][y][d] = 0;
            int nr = x + dr[d];
            int nc = y + dc[d];

            if (check(nr, nc) && map[nr][nc] != '*') {
                vis[nr][nc][d] = 0;
                pq.offer(new int [] {nr, nc, d, 0});
            }
        }

        bfs();

        /*
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(vis[r][c] == Integer.MAX_VALUE ? -1 : vis[r][c]);
            }
            System.out.println();
        }
        */
        
        System.out.println(ans);
    }

    static void bfs() {

        while (!pq.isEmpty()) {
            int [] q = pq.poll();

            if (map[q[0]][q[1]] == 'C') ans = Math.min(ans, q[3]);

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && map[nr][nc] != '*') {
                    int nextD = d == q[2] ? q[3] : q[3] + 1;
                    if (vis[nr][nc][d] > nextD) {
                        vis[nr][nc][d] = nextD;
                        pq.offer(new int [] {nr, nc, d, nextD});
                    }
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}