import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char [][] map;
    static int [][] visited;
    static int n, h, d, k, ans=-1;
    static Queue<int[]> que = new LinkedList<>();
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') que.offer(new int [] {i, j, h, 0, 0});
            }
        }

        visited = new int [n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }

        bfs();
        
        System.out.println(ans);
    }

    static void bfs() {
        while(!que.isEmpty()) {
            int [] q = que.poll();
            int r = q[0];
            int c = q[1];
            int h1 = q[2];
            int cnt = q[3];
            int k1 = q[4];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (check(nr, nc, h1 + k1) && h1 + k1 > 0) {
                    visited[nr][nc] = h1 + k1;
                    if (map[nr][nc] == 'U') que.offer(new int [] {nr, nc, h1, cnt+1, d-1});
                    else if (map[nr][nc] == 'E') {
                        ans = cnt+1;
                        return;
                    } else {
                        if (k1 == 0 && h1 > 0) que.offer(new int [] {nr, nc, h1-1, cnt+1, 0});
                        else if (k1 > 0) que.offer(new int [] {nr, nc, h1, cnt+1, k1-1});
                    }
                }
            }
        }
    }

    static boolean check(int r, int c, int x) {
        return r >= 0 && r < n && c >= 0 && c < n && visited[r][c] < x;
    }
}