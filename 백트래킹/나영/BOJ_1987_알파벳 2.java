import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, max;
    static char [][] map;
    static boolean [] used = new boolean [26];
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char [R][];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        used[map[0][0] - 'A'] = true;

        dfs(0, 0, 1);
        
        System.out.println(max);
    }

    static void dfs(int r, int c, int cnt) {
        max = Math.max(max, cnt);
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d]; int nc = c + dc[d];
            if (check(nr, nc) && !used[map[nr][nc]-'A']) {
                used[map[nr][nc]-'A'] = true;
                dfs(nr, nc, cnt+1);
                used[map[nr][nc]-'A'] = false;
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}