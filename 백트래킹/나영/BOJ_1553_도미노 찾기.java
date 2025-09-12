import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int [][] map = new int [8][7];
    static String s;
    static int ans;
    static boolean [][] vis = new boolean [8][7];
    static boolean [][] visNum = new boolean [7][7];
    public static void main(String[] args) {
        for (int r = 0; r < 8; r++) {
            s = sc.next(); 
            for (int c = 0; c < 7; c++) {
                map[r][c] = s.charAt(c) - '0';
            }
        }

        dfs (0);
        
        System.out.println(ans);
    }

    static void dfs (int idx) {
        if (idx == 56) {
            ans++;
            return;
        }

        int r = idx / 7;
        int c = idx % 7;

        if (vis[r][c]) dfs(idx+1);
        else {
            vis[r][c] = true;
            int a = map[r][c];
            if (c+1 < 7 && !vis[r][c+1] && check(r, c, r, c+1)) {
                int b = map[r][c+1];
                if (a > b) visNum[b][a] = true;
                else visNum[a][b] = true;
                
                vis[r][c+1] = true;
                dfs(idx+1);
                vis[r][c+1] = false;
                
                if (a > b) visNum[b][a] = false;
                else visNum[a][b] = false;
            }

            if (r+1 < 8 && !vis[r+1][c] && check(r, c, r+1, c)) {
                int b = map[r+1][c];
                if (a > b) visNum[b][a] = true;
                else visNum[a][b] = true;
                
                vis[r+1][c] = true;
                dfs(idx+1);
                vis[r+1][c] = false;
                
                if (a > b) visNum[b][a] = false;
                else visNum[a][b] = false;
            }

            vis[r][c] = false;
        }
    }

    static boolean check (int r1, int c1, int r2, int c2) {
        int a = map[r1][c1];
        int b = map[r2][c2];
        if (a > b) return !visNum[b][a];
        return !visNum[a][b];
    }
}