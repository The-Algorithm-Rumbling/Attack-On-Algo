import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, n, m, R;
    static int [][] map;
    static int [] dr = {1, 0, -1, 0};
    static int [] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int [N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        n = N;
        m = M;

        for (int i = 0; i < Math.min(N, M) / 2; i++) {
            find(i, 2 * n + 2 * m - 4);
            n -= 2;
            m -= 2;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(map[r][c]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static void find (int start, int len) {
        int size = R % len;

        for (int i = 0; i < size; i++) {
            int r = start;
            int c = start;
            int tmp1 = map[r][c];
            int tmp2 = 0;
            int d = 0;

            while (d < 4) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= start && nr < N - start && nc >= start && nc < M - start) {
                    tmp2 = map[nr][nc];
                    map[nr][nc] = tmp1;
                    tmp1 = tmp2;
                    r = nr;
                    c = nc;
                } else d++;
            }
        }
    }
}