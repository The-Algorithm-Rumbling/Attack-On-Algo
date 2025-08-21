import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans;
    static char map [][];
    static boolean visited [][];
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static class P {
        int r, c, cnt;

        P (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char [n][];
        
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                visited = new boolean [n][m];
                if (map[r][c] == 'L') {
                    bfs(r, c);
                }
            }
        }

        System.out.println(ans);
    }

    static void bfs(int r, int c) {
        Queue<P> que = new LinkedList<>();
        visited[r][c] = true;
        que.offer(new P (r, c, 0));

        while (!que.isEmpty()) {
            P p = que.poll();

            if (ans < p.cnt) ans = p.cnt;

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (check(nr, nc) && map[nr][nc] == 'L' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    que.offer(new P (nr, nc, p.cnt+1));
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}