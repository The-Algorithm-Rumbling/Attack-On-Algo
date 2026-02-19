import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, min=10000000;
    static int [][] map;
    static boolean [] vis;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n][n];
        vis = new boolean [n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        vis[0] = true;
        dfs(0, 1, 0);

        System.out.println(min);
    }

    static void dfs(int tmp, int depth, int sum) {
        if (depth == n) {
            if (map[tmp][0] != 0) min = Math.min(min, sum + map[tmp][0]);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            if (map[tmp][i] == 0) continue;
            vis[i] = true;
            dfs(i, depth+1, sum+map[tmp][i]);
            vis[i] = false;
        }
    }
}