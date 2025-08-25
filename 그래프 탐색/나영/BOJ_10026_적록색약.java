import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, a, b;
    static char [][] map1, map2;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
    static void dfs (int r, int c, int num, char ch) {
        if (num == 1) {
            map1[r][c] = '.';

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (check(nr, nc) && map1[nr][nc] == ch) {
                    dfs(nr, nc, num, ch);
                }
            }
        } else {
            map2[r][c] = '.';

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (check(nr, nc) && map2[nr][nc] == ch) {
                    dfs(nr, nc, num, ch);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map1 = new char [n][];
        map2 = new char [n][n];

        for (int r = 0; r < n; r++) {
            map1[r] = br.readLine().toCharArray();
            for (int c = 0; c < n; c++) {
                if (map1[r][c] == 'R' || map1[r][c] == 'G') map2[r][c] = 'R';
                else map2[r][c] = map1[r][c];
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map1[r][c] != '.') {
                    a++;
                    dfs(r, c, 1, map1[r][c]);
                }
                if (map2[r][c] != '.') {
                    b++;
                    dfs(r, c, 2, map2[r][c]);
                }
            }
        }
        
        
        System.out.println(a + " " + b);
    }
}