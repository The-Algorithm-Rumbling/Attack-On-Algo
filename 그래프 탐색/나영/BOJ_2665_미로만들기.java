import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [][] map;
    static int [][] vis;
    static int n, m, ans=Integer.MAX_VALUE;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        map = new char [n][];

        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
        }

        m = map[0].length;
        vis = new int [n][m];

        for (int r = 0; r < n; r++) {
            Arrays.fill(vis[r], ans);
        }

        bfs();
        
        System.out.println(ans);
    }

    static void bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {0, 0, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == n-1 && q[1] == m-1) ans = Math.min(ans, q[2]);

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && vis[nr][nc] > q[2]) {
                    vis[nr][nc] = q[2];
                    if (map[nr][nc] == '0') que.offer(new int [] {nr, nc, q[2]+1});
                    else que.offer(new int [] {nr, nc, q[2]});
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}