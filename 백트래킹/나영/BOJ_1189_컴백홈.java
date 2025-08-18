import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, K, ans;
    static char [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char [R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        map[R-1][0] = 'T';
        dfs(R-1, 0, 1);
        
        System.out.println(ans);
    }

    static void dfs (int r, int c, int cnt) {
        if (cnt == K && r == 0 && c == C-1) {
            ans++;
            return;
        }
        
        if (cnt >= K) return;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && map[nr][nc] != 'T') {
                map[nr][nc] = 'T';
                dfs(nr, nc, cnt + 1);
                map[nr][nc] = '.';
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}