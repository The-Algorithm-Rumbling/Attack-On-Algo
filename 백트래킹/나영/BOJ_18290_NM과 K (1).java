import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, k; 
    static long ans = Long.MIN_VALUE;
    static int [][] map;
    static boolean [][] visited;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        map = new int [n][m];
        visited = new boolean [n][m];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int cnt, long sum) {
        if (cnt == k) {
            ans = Math.max(ans, sum);
            return;
        }
        
        if (idx == n*m) return;

        int r = idx / m;
        int c = idx % m;

        if (!visited[r][c] && vis(r, c)) {
            visited[r][c] = true;
            dfs(idx + 1, cnt + 1, sum + map[r][c]);
            visited[r][c] = false;
        }

        dfs(idx + 1, cnt, sum);
    }

    static boolean vis(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && visited[nr][nc]) {
                return false;
            }
        }

        return true;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}