import java.util.*;
import java.lang.*;
import java.io.*;

class P {
    int r, c;

    public P (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, cnt, days, before;
    static StringTokenizer st;
    static int cheese[][];
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int [n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                cheese[r][c] = Integer.parseInt(st.nextToken());
                if (cheese[r][c] == 1) cnt++;
            }
        }

        while (true) {
            visited = new boolean [n][m];
            bfs();

            visited = new boolean [n][m];
            before = cnt;
            cnt = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (cheese[r][c] == 1 && !visited[r][c]) {
                        visited[r][c] = true;
                        dfs(r, c);
                    }
                }
            }
            
            days++;
            if (cnt == 0) break;
        }
        
        System.out.println(days);
        System.out.println(before);
    }

    static void bfs() {
        Queue<P> que = new LinkedList<>();
        visited[0][0] = true;
        que.offer(new P (0 ,0));

        while (!que.isEmpty()) {
            P p = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (check(nr, nc)) {
                    if (cheese[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        cheese[nr][nc] = 0;
                    } else if (cheese[nr][nc] == 0 && !visited[nr][nc]) {
                        que.offer(new P(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

    static void dfs(int r, int c) {
        cnt++;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && cheese[nr][nc] == 1 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}