import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, L, R, ans, num;
    static int [][] map, map2;
    static boolean [][] vis, vis2;
    static boolean isS = true;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static class P {
        int r, c;

        P(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int [n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            isS = false;
            map2 = new int [n][n];
            vis = new boolean[n][n];
            num = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!vis[i][j]) {
                        bfs(i, j);
                        num++;
                    }
                }
            }

            if (!isS) break;
            ans++;
        }

        System.out.println(ans);
    }

    static void bfs(int r, int c) {
        Queue<P> que = new LinkedList<>();
        map2[r][c] = num;
        vis[r][c] = true;
        int sum = map[r][c];
        int cnt = 1;
        que.offer(new P(r, c));

        while (!que.isEmpty()) {
            P p = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (check(nr, nc) && !vis[nr][nc]) {
                    int people = Math.abs(map[p.r][p.c] - map[nr][nc]);
                    if (people >= L && people <= R) {
                        que.offer(new P(nr, nc));
                        vis[nr][nc] = true;
                        cnt++;
                        sum += map[nr][nc];
                        map2[nr][nc] = num;
                    }
                }
            }
        }

        if (cnt > 1) isS = true;

        vis2 = new boolean[n][n];
        dfs(r, c, sum/cnt);
    }

    static void dfs(int r, int c, int people) {
        map[r][c] = people;
        vis2[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && !vis2[nr][nc] && map2[nr][nc] == num) {
                dfs(nr, nc, people);
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}