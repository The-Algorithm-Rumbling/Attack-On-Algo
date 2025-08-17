import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [][] map;
    static int n, m, a, b;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();

        map = new char [m][n];

        for (int i = 0; i < m; i++) {
            map[i] = sc.next().toCharArray();
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] == 'W') {
                    map[r][c] = 'N';
                    a += Math.pow(dfs(r, c, 'W'), 2);
                } else if (map[r][c] == 'B') {
                    map[r][c] = 'N';
                    b += Math.pow(dfs(r, c, 'B'), 2);
                }
            }
        }
        
        System.out.println(a + " " + b);
    }

    static int dfs(int r, int c, char ex) {
        int cnt = 1;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && map[nr][nc] == ex) {
                map[nr][nc] = 'N';
                cnt += dfs(nr, nc, ex);
            }
        }

        return cnt;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
    
}