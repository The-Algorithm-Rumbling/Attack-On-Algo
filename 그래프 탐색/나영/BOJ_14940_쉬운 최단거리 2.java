import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, x, y;
    static int [][] map, ans;
    static boolean [][] vis;
    static int [] dr = {-1, 1, 0, 0};
    static int [] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];
        ans = new int [n][m];
        vis = new boolean [n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    x = i;
                    y = j;
                } else if (map[i][j] == 1) ans[i][j] = -1;
            }
        }
        
        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) sb.append(ans[i][j]).append(" ");
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {x, y, 0});
        vis[x][y] = true;

        while (!que.isEmpty()) {
            int [] q = que.poll();
            int r = q[0]; int c = q[1]; int cnt = q[2];
            ans[r][c] = cnt;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d]; int nc = c + dc[d];

                if (check(nr, nc) && map[nr][nc] == 1 && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    que.offer(new int [] {nr, nc, cnt+1});
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}