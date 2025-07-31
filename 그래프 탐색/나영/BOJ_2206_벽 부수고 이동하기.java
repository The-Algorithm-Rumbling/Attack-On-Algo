import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static char [][] map;
    static boolean [][][] visited;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char [n][m];
        visited = new boolean [n][m][2];

        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0, 1, 1});
        visited[0][0][0] = true;

        while(!que.isEmpty()) {
            int [] q = que.poll();
            int r = q[0];
            int c = q[1];
            int wall = q[2];
            int cnt = q[3];

            if (r == n-1 && c == m-1) return cnt;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (check(nr, nc)) {
                    if (map[nr][nc] == '1' && wall > 0 && !visited[nr][nc][wall]) {
                        visited[nr][nc][wall] = true;
                        que.offer(new int[] {nr, nc, 0, cnt+1});
                    } else if (map[nr][nc] == '0' && !visited[nr][nc][wall]) {
                        visited[nr][nc][wall] = true;
                        que.offer(new int[] {nr, nc, wall, cnt+1});
                    }
                }
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}