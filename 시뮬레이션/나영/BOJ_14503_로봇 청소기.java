import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, x, y, dis, cnt;
    static int [][] map;
    static boolean [][] visB, visR;
                    //  북 동 남  서
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int [n][m];

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dis = Integer.parseInt(st.nextToken());
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        find();
        
        System.out.println(cnt);
    }

    static void find() {
        while (true) {
            int d = dis;
            boolean isS = false;

            if (map[x][y] == 0) {
                map[x][y] = 2;
                cnt++;
            }

            // 4칸 중 청소되지 않은 빈 칸이 있는 경우
            for (int i = 0; i < 4; i++) {
                d = d-1 < 0 ? 3 : d-1;
                int nr = x + dr[d];
                int nc = y + dc[d];

                if (map[nr][nc] < 1) {
                    x = nr; y = nc; dis = d;
                    isS = true;
                    break;
                }
            }

            // 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!isS) {
                int nr = x + dr[(dis + 2) % 4];
                int nc = y + dc[(dis + 2) % 4];

                if (map[nr][nc] == 1) return;
                x = nr; y = nc;
            }
        }
    }
}