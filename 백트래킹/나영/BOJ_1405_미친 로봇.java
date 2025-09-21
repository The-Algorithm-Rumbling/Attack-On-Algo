import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static double ans;
    static double [] arr = new double [4];
    static int [] dr = {1, -1, 0, 0};
    static int [] dc = {0, 0, 1, -1};
    static boolean [][] vis = new boolean [29][29];
    public static void main(String[] args) {
        n = sc.nextInt();
        vis[14][14] = true;

        for (int i = 0; i < 4; i++) arr[i] = sc.nextDouble() / 100;

        dfs(0, 1, 14, 14);
        
        System.out.println(ans);
    }

    static void dfs (int depth, double odds, int r, int c) {
        if (depth == n) {
            ans += odds;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (vis[nr][nc]) continue;
            vis[nr][nc] = true;
            double newOdds = odds * arr[d];
            dfs(depth+1, newOdds, nr, nc);
            vis[nr][nc] = false;
        }
    }
}