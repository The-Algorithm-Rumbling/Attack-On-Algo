import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, ans = 1;
    static char [][] map;
    static boolean [] used = new boolean [26];
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        map = new char [n][];

        for (int i = 0; i < n; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        used[map[0][0] - 'A'] = true;

        dfs(0, 0, 1);
        
        System.out.println(ans);
    }

    static void dfs (int r, int c, int cnt) {
        ans = Math.max(ans, cnt);
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && !used[map[nr][nc] - 'A']) {
                used[map[nr][nc] - 'A'] = true;

                dfs(nr, nc, cnt+1);
                
                used[map[nr][nc] - 'A'] = false;
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}