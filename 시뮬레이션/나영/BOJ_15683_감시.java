import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, cnt, ans=Integer.MAX_VALUE;
    static int [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static List<int []> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int [n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] > 0 && map[r][c] < 6) list.add(new int [] {r, c});
            }
        }

        dfs(0);

        System.out.print(ans);
    }

    static void dfs(int tmp) {
        if (tmp == list.size()) {
            cnt = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (map[r][c] == 0) cnt++;
                }
            }
            
            ans = Math.min(cnt, ans);
            return;
        }

        int r = list.get(tmp)[0];
        int c = list.get(tmp)[1];

        if (map[r][c] == 1) {
            for (int d = 0; d < 4; d++) {
                change (r, c, d, -1);
                dfs (tmp + 1);
                change (r, c, d, 1);
            }
        } else if (map[r][c] == 2) {
            for (int d = 0; d < 2; d++) {
                change (r, c, d, -1);
                change (r, c, d+2, -1);
                dfs(tmp+1);
                change (r, c, d, 1);
                change (r, c, d+2, 1);
            } 
        } else if (map[r][c] == 3) {
            for (int d = 0; d < 4; d++) {
                change (r, c, d, -1);
                change (r, c, (d+1)%4, -1);
                dfs (tmp + 1);
                change (r, c, d, 1);
                change (r, c, (d+1)%4, 1);
            }
        } else if (map[r][c] == 4) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < 3; i++) {
                    change (r, c, (d+i)%4, -1);
                }
                dfs(tmp+1);
                for (int i = 0; i < 3; i++) {
                    change (r, c, (d+i)%4, 1);
                }

            }
        } else {
            for (int d = 0; d < 4; d++) {
                change (r, c, d, -1);
            }
            
            dfs (tmp + 1);

            for (int d = 0; d < 4; d++) {
                change (r, c, d, 1);
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m && map[r][c] != 6;
    }

    static void change(int r, int c, int d, int num) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (check(nr, nc)) {
            if ((num == 1 && map[nr][nc] < 0) || (num == -1 && map[nr][nc] <= 0)) {
                map[nr][nc] += num;
            }
            
            nr += dr[d];
            nc += dc[d];
        }
    }
}