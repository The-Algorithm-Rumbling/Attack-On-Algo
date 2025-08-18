import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, ans=1000;
    static int [][] map;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n] [n];
        selected = new boolean [n];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs (int start, int cnt) {
        if (cnt == n/2) {
            int team1 = 0, team2 = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    if (selected[i] && selected[j]) team1 += map[i][j] + map[j][i];
                    else if (!selected[i] && !selected[j]) team2 += map[i][j] + map[j][i];
                }
            }
            
            ans = Math.min(ans, Math.abs(team1-team2));
            return;
        }

        if (start == n) return;

        for (int i = start; i < n; i++) {
            selected[i] = true;
            dfs(i+1, cnt+1);
            selected[i] = false;
        }
    }
}