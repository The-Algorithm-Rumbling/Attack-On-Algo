import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, A, B, k, x, y, endX, endY;
    static boolean [][] map1, visited;
                    //  상 우 하 좌
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map1 = new boolean [n+1][m+1];
        visited = new boolean [n+1][m+1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map1[a][b] = true;
        }
        
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {x, y, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == endX && q[1] == endY) return q[2];

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc, d) && findWall(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    que.offer(new int [] {nr, nc, q[2] + 1});
                }
            }
        }

        return -1;
    }

    static boolean check(int r, int c, int d) {
        //  상 우 하 좌
        if (d == 0) {
            return r >= 1;
        }
        if (d == 1) {
            return c+B-1 <= m;
        }
        if (d == 2) {
            return r+A-1 <= n;
        }
        if (d == 3) {
            return c >= 1;
        }

        return true;
    }

    static boolean findWall(int r, int c) {
        for (int nr = r; nr < r + A; nr++) {
            for (int nc = c; nc < c + B; nc++) {
                if (map1[nr][nc]) return false;
            }
        }

        return true;
    }
}