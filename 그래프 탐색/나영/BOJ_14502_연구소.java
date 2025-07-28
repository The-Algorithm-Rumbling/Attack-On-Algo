import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, max;
    static int map [][];
    static boolean visited [][];
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        
        System.out.println(max);
    }

    static void dfs(int r, int c, int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int nr = r; nr < n; nr++) {
            for (int nc = 0; nc < m; nc++) {
                if (nr == r && nc < c) continue;
                if (map[nr][nc] == 0) {
                    map[nr][nc] = 1;
                    dfs(nr, nc, cnt+1);
                    map[nr][nc] = 0;
                }
            }
        }

    }

    static void bfs() {
        visited = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if(map[r][c] != 0) {
                    visited[r][c] = true;
                    if (map[r][c] == 2) que.offer(new int [] {r, c});
                }
            }
        }

        while (!que.isEmpty()) {
            int [] q = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    que.offer(new int[] {nr, nc});
                }
            }
        }

        find ();
    }

    static void find() {
        int cnt = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if(!visited[r][c]) cnt++;
            }
        }

        max = Math.max(max, cnt);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}