import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, R, ans;
    static int [][] map;
    static char [][] result;
                    //  우  좌 하 상
    static int [] dr = {0, 0, 1, -1};
    static int [] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int [n][m];
        result = new char [n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(result[r], 'S');
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            String s = st.nextToken();

            if (s.charAt(0) == 'E') changeMap(r1, c1, 0);
            else if (s.charAt(0) == 'W') changeMap(r1, c1, 1);
            else if (s.charAt(0) == 'S') changeMap(r1, c1, 2);
            else changeMap(r1, c1, 3);
            
            st = new StringTokenizer(br.readLine());
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;

            result[r2][c2] = 'S';
        }

        System.out.println(ans);
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                System.out.print(result[r][c] + " ");
            }
            System.out.println();
        }
    }

    static void changeMap (int r, int c, int d) {
        int cnt = map[r][c]-1;
        result[r][c] = 'F';
        ans++;
        while (check(r, c) && cnt > 0) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            cnt--;

            if (check(nr, nc)) {
                if (result[nr][nc] != 'F') {
                    cnt = Math.max(cnt, map[nr][nc]-1);
                    ans++;
                }
                result[nr][nc] = 'F';
            }

            r = nr;
            c = nc;
        }
    }

    static boolean check (int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}