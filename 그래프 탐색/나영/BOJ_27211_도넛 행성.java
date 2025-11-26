import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans;
    static int map [][];
    static boolean [][] vis;
    static int dr [] = {-1, 0, 1, 0};
    static int dc [] = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];
        vis = new boolean [n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 0 && !vis[r][c]) {
                    ans++;
                    bfs(r, c);
                }
            }
        }
        
        System.out.println(ans);
    }

    static void bfs(int r, int c) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{r, c});
        vis[r][c] = true;
    
        while (!que.isEmpty()) {
            int[] q = que.poll();
    
            for (int d = 0; d < 4; d++) {
                int nr = (q[0] + dr[d] + n) % n;
                int nc = (q[1] + dc[d] + m) % m;
    
                if (!vis[nr][nc] && map[nr][nc] == 0) {
                    vis[nr][nc] = true;
                    que.add(new int[]{nr, nc});
                }
            }
        }
    }
}