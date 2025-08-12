import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, ans;
    static int [][] map;
    static boolean [][] visited;
    static int [][] dr = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int [][] dc = {{-1, 0}, {-1, 0}, {0, 1}, {0, 1}};
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean [n][m];
        map = new int [n][m];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = sc.nextInt();
            }
        }
        
        boomerang(0, 0, 0);
        System.out.println(ans);
    }

    static void boomerang(int r, int c, int max) {
        if (r == n) {
            ans = Math.max(max, ans);
            return;
        }
        
        if(!visited[r][c]) {
            for(int d = 0; d < 4; d++) {
                int nr1 = r + dr[d][0];
                int nr2 = r + dr[d][1];
                
                int nc1 = c + dc[d][0];
                int nc2 = c + dc[d][1];
    
                if (check(nr1, nc1) && check(nr2, nc2)) {
                    visited[r][c] = true;
                    visited[nr1][nc1] = true;
                    visited[nr2][nc2] = true;
                    int sum = max + map[r][c]*2 + map[nr1][nc1] + map[nr2][nc2];
    
                    if (c == m-1) boomerang(r+1, 0, sum);
                    else boomerang(r, c+1, sum);
                    
                    visited[r][c] = false;
                    visited[nr1][nc1] = false;
                    visited[nr2][nc2] = false;
                }
            }
        }
        
        if (c == m-1) boomerang(r+1, 0, max);
        else boomerang(r, c+1, max);
    } 

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m && !visited[r][c];
    }
}