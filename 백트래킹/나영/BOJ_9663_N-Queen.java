import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, ans;
    static boolean [][] visited;
    static int [] dr = {1, 1};
    static int [] dc = {-1, 1};
    public static void main(String[] args) {
        n = sc.nextInt();
        visited = new boolean [n][n];
        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int r) {
        if (r == n) {
            ans++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if(visited[r][c] || !check(r, c)) continue;
            visited[r][c] = true;
            dfs(r+1);
            visited[r][c] = false;
        }
    }

    static boolean check(int r, int c) {
        int nr = r-1;

        // 위 확인 
        while(nr >= 0) {
            if (visited[nr][c]) return false;
            nr--;
        }

        // 대각선 확인 1 
        nr = r-1;
        int nc = c-1;

        while(nr >= 0 && nc >= 0) {
            if (visited[nr][nc]) return false;
            nr--;
            nc--;
        }

        // 대각선 확인 2 
        nr = r-1;
        nc = c+1;

        while(nr >= 0 && nc < n) {
            if (visited[nr][nc]) return false;
            nr--;
            nc++;
        }

        return true;
    }
}