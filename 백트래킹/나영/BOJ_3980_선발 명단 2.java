import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max;
    static int [][] map = new int [11][11];
    static boolean [] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        while (n > 0) {
            max = 0;
            vis = new boolean [11];
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            dfs(0, 0);
            sb.append(max).append("\n");
            n--;
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth, int sum) {
        if (depth == 11) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (map[depth][i] == 0 || vis[i]) continue;
            vis[i] = true;
            dfs(depth+1, sum+map[depth][i]);
            vis[i] = false;
        }
    }
}