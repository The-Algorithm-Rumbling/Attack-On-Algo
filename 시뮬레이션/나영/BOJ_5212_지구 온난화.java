import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, sR, sC;
    static char [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char [n][];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        reMap();

        for (int r = 0; r < n; r++) {
            if (findR(r)) sR++;
            else break;
        }

        for (int r = n-1; r >= 0; r--) {
            if (findR(r)) n--;
            else break;
        }

        for (int c = 0; c < m; c++) {
            if (findC(c)) sC++;
            else break;
        }

        for (int c = m-1; c >= 0; c--) {
            if (findC(c)) m--;
            else break;
        }

        for (int r = sR; r < n; r++) {
            for (int c = sC; c < m; c++) {
                System.out.print(map[r][c] == '*' ? '.' : map[r][c]);
            }
            System.out.println();
        }
        
    }

    static boolean findR(int r) {
        for (int c = 0; c < m; c++) {
            if (map[r][c] == 'X') {
                return false;
            }
        }
        
        return true;
    }

    static boolean findC(int c) {
        for (int r = 0; r < n; r++) {
            if (map[r][c] == 'X') {
                return false;
            }
        }

        return true;
    }

    static void reMap() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 'X') {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!check(nr, nc) || map[nr][nc] == '.') cnt++;
                    }

                    if (cnt >= 3) map[r][c] = '*';
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}