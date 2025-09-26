import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, ans;
    static boolean [] vis;
    static int [][] players;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            players = new int [11][11];
            vis = new boolean [11];

            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    players[j][k] = sc.nextInt();
                }
            }

            sb.append(dfs(0, 0)).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static int dfs(int depth, int sum) {
        if (depth == 11) {
            return sum;
        }

        int ans = 0;
        
        for (int i = 0; i < 11; i++) {
            if (players[depth][i] == 0 || vis[i]) continue;
            
            vis[i] = true;
            ans = Math.max(ans, dfs(depth+1, sum + players[depth][i]));
            vis[i] = false;
        }
        
        return ans;
    }
}