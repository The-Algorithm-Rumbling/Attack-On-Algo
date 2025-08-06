import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, min=Integer.MAX_VALUE;
    static int map[][];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        map = new int [n][n];
        visited = new boolean [n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, i, 1, 0);
            visited[i] = false;
        }
        
        System.out.println(min);
    }

    static void dfs(int curr, int start, int cnt, int sum) {
        if (cnt == n) {
            if (map[curr][start] != 0) min = Math.min(min, sum + map[curr][start]);
            return;
        }
        
        for (int c = 0; c < n; c++) {
            if (visited[c] || map[curr][c] == 0) continue;
            visited[c] = true;
            dfs(c, start, cnt+1, sum + map[curr][c]);
            visited[c] = false;
        }
    }
}