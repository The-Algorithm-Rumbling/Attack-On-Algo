import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] map;
    static int n, m, ans;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static Queue <int[]> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int [n][m];

        for (int r = 0; r < n; r++) {
            char [] charn = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                map[r][c] = charn[c] - '0';
                if (map[r][c] == 2) {
                    que.offer(new int [] {r, c, 0});
                    map[r][c] = 1;
                }
            }
        }
        
        ans = bfs();
        
        System.out.println(ans != -1 ? "TAK\n" + ans: "NIE");
    }

    static int bfs() {
        while(!que.isEmpty()) {
            int[] q = que.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && map[nr][nc] != 1) {
                    if (map[nr][nc] > 2) return q[2] + 1;
                    map[nr][nc] = 1;
                    que.offer(new int [] {nr, nc, q[2] + 1});
                }
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
    
}