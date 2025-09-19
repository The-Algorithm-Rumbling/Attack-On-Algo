import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m, n, s1, s2, d1, e1, e2, d2;
    static int [][] map;
    static int [][][] vis;
                    //  북  서 남 동
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        map = new int [m][n];
        vis = new int [m][n][4];

        for (int r = 0; r < m; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                Arrays.fill(vis[r][c], Integer.MAX_VALUE);
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        s1 = Integer.parseInt(st.nextToken())-1;
        s2 = Integer.parseInt(st.nextToken())-1;
        d1 = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        e1 = Integer.parseInt(st.nextToken())-1;
        e2 = Integer.parseInt(st.nextToken())-1;
        d2 = Integer.parseInt(st.nextToken());

        d1 = findD(d1);
        d2 = findD(d2);
        
        System.out.println(bfs());
    }

    static int findD (int d) {
        if (d == 1) return 3;
        else if (d == 2) return 1;
        else if (d == 3) return 2;
        return 0;
    }

    static int bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {s1, s2, d1});
        vis[s1][s2][d1] = 0;

        while (!que.isEmpty()) {
            int [] q = que.poll();
            int r = q[0];
            int c = q[1];
            int d = q[2];
            // System.out.println(r + " " + c + " " + d);
            int next = vis[r][c][d] + 1;

            if (r == e1 && c == e2 && d == d2) return vis[r][c][d];

            int nr = r;
            int nc = c;
            
            // 해당 방향으로 직진
            for (int k = 1; k <= 3; k++) {
                nr += dr[d];
                nc += dc[d];

                if (!check(nr, nc) || map[nr][nc] == 1) break;

                if (vis[nr][nc][d] > next) {
                    vis[nr][nc][d] = next;
                    que.offer(new int [] {nr, nc, d});
                }
            }
            
            // 방향 변경
            int minus = d-1 < 0 ? 3 : d-1;
            if (vis[r][c][minus] > next) {
                vis[r][c][minus] = next;
                que.offer(new int [] {r, c, minus});
            }

            int plus = d+1 > 3 ? 0 : d+1;
            if (vis[r][c][plus] > next) {
                vis[r][c][plus] = next;
                que.offer(new int [] {r, c, plus});
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}