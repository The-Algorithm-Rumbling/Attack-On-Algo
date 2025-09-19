import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, cnt = 2, ans = Integer.MAX_VALUE;
    static int [][] map, vis;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n][n];
        vis = new int [n][n];
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(vis[r], Integer.MAX_VALUE);
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] == 1) {
                    dfs(r, c);
                    cnt++;
                }
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] != 0) {
                    bfs(r, c);
                }
            }
        }

        System.out.println(ans);
        
    }

    static void dfs(int r, int c) {
        map[r][c] = cnt;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }

    static void bfs(int r, int c) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int [] {r, c});
        vis[r][c] = 0;
        int num = map[r][c];

        while (!que.isEmpty()) {
            int [] q = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                int next = vis[q[0]][q[1]] + 1;
    
                if (check(nr, nc)) {
                    if (map[nr][nc] != 0 && map[nr][nc] != num) {
                        ans = Math.min(ans, vis[q[0]][q[1]]);
                        return;
                    }
                    
                    if (map[nr][nc] == 0 && vis[nr][nc] > next) {
                        vis[nr][nc] = next;
                        que.offer(new int [] {nr, nc});
                    }
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}