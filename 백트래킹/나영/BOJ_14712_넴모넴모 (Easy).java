import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, ans;
    static boolean [][] visited;
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean [n][m];

        dfs(0);
        
        System.out.println(ans);
    }

    static void dfs(int depth) {
        if (depth == n * m) {
            if (check()) ans++;
            return;
        }

        int r = depth / m;
        int c = depth % m;

        visited[r][c] = true;
        dfs(depth + 1);
        visited[r][c] = false;
        dfs(depth + 1);
        
    }

    static boolean check() {
        for (int r = 0; r < n-1; r++) {
            for (int c = 0; c < m-1; c++) {
                if (visited[r][c] && visited[r+1][c] && visited[r][c+1] && visited[r+1][c+1]) return false;
            }
        }

        return true;
    }
}