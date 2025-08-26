import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char [][] map;
    static int n, m, ans;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static Queue<int []> que = new LinkedList<>();
    static Queue<int []> water = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char [n][m];

        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 'S') que.offer(new int [] {r, c, 0});
                else if (map[r][c] == '*') water.offer(new int [] {r, c});
            }
        }

        ans = bfs();
        
        System.out.println(ans != -1 ? ans : "KAKTUS");
    }

    static int bfs() {
        while (!que.isEmpty()) {

            int size = que.size();

            while(size > 0) {
                int [] q = que.poll();
                
                if (map[q[0]][q[1]] == '*') {
                    size--;
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = q[0] + dr[d];
                    int nc = q[1] + dc[d];

                    if (check(nr, nc)) {
                        if (map[nr][nc] == '.') {
                            map[nr][nc] = 'S';
                            que.offer(new int [] {nr, nc, q[2] + 1});
                        } else if (map[nr][nc] == 'D') return q[2] + 1;
                    }
                }
                size--;
            }
            
            int wSize = water.size();

            while (wSize > 0) {
                int [] w = water.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = w[0] + dr[d];
                    int nc = w[1] + dc[d];

                    if (check(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] == 'S')) {
                        map[nr][nc] = '*';
                        water.offer(new int [] {nr, nc});
                    }
                }
                wSize--;
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}