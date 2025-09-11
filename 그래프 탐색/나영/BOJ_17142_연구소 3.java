import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean [][] vis;
    static int n, m, cnt, ans=Integer.MAX_VALUE;
    static int [][] map;
    static int [][] arr;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static List<int[]> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int [n][n];
        vis = new boolean [n][n];
        arr = new int [m][2];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 1) cnt++;
                if (map[r][c] == 2) list.add(new int [] {r, c});
            }
        }

        if (list.size() == cnt) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);
        
        System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
    }

    static void dfs (int idx, int depth) {
        if (depth == m) {
            bfs();
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            arr[depth][0] = list.get(i)[0];
            arr[depth][1] = list.get(i)[1];
            dfs(i+1, depth+1);
        }
    }

    static void bfs () {
        Queue<int[]> que = new LinkedList<>();
        vis = new boolean [n][n];
        int sum = cnt;
        int max = 0;
        for (int [] i : arr) {
            que.offer(new int [] {i[0], i[1], 0});
            vis[i[0]][i[1]] = true;
            sum--;
        }

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (map[q[0]][q[1]] == 0) max = q[2];

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && !vis[nr][nc] && map[nr][nc] != 1) {
                    vis[nr][nc] = true;
                    que.offer(new int [] {nr, nc, q[2] + 1});
                    sum--;
                }
            }
        }

        if (sum == 0) ans = Math.min(ans, max);
    }

    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}