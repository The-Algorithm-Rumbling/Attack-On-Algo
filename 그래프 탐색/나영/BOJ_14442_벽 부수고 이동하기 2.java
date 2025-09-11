import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][][] vis;
    static int n, m, k, ans=Integer.MAX_VALUE;
    static int [][] map, map2;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int [n][m];
        vis = new int [n][m][k+1];
        map2 = new int [n][m];

        for (int r = 0; r < n; r++) {
            char [] charn = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                Arrays.fill(vis[r][c], ans);
                map[r][c] = charn[c] - '0';
            }
        }
        
        System.out.println(bfs());
    }

    static int bfs () {
        Queue<int[]> que = new LinkedList<>();
        vis[0][0][0] = 0;
        que.offer(new int [] {0, 0, 0});
        
        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == n-1 && q[1] == m-1) {
                return vis[q[0]][q[1]][q[2]]+1;
            }

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];
        
                if (check(nr, nc)) {
                    if (map[nr][nc] == 0 && vis[nr][nc][q[2]] > vis[q[0]][q[1]][q[2]] + 1) {
                        vis[nr][nc][q[2]] = vis[q[0]][q[1]][q[2]] + 1;
                        que.offer(new int [] {nr, nc, q[2]});
                    }
                    else {
                        if (q[2] + 1 <= k && vis[nr][nc][q[2]+1] > vis[q[0]][q[1]][q[2]] + 1) {
                            vis[nr][nc][q[2]+1] = vis[q[0]][q[1]][q[2]] + 1;
                            que.offer(new int [] {nr, nc, q[2] + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }

    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}