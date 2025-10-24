import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, xr, yr, xb, yb;
    static char [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char [n][];
        
        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 'R') {
                    xr = r;
                    yr = c;
                } else if (map[r][c] == 'B') {
                    xb = r;
                    yb = c;
                }
            }
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int []> que = new LinkedList<>();
        boolean[][][][] vis = new boolean[n][m][n][m];
        que.offer(new int [] {xr, yr, xb, yb, 0});

        while(!que.isEmpty()) {
            int [] q = que.poll();
            int rr = q[0];
            int cr = q[1];
            int rb = q[2];
            int cb = q[3];
            int cnt = q[4];
            
            if (cnt >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                int nrb = rb; int ncb = cb;
                int nrr = rr; int ncr = cr;
                int cntR = 0; int cntB = 0;

                while (map[nrb+dr[d]][ncb+dc[d]] != '#' && map[nrb][ncb] != 'O') {
                    nrb += dr[d]; ncb += dc[d]; cntB++;
                }

                while (map[nrr+dr[d]][ncr+dc[d]] != '#' && map[nrr][ncr] != 'O') {
                    nrr += dr[d]; ncr += dc[d]; cntR++;
                }
                
                if (map[nrb][ncb] == 'O') continue;
                if (map[nrr][ncr] == 'O') return cnt+1;

                if (nrb == nrr && ncb == ncr) {
                    if (cntB > cntR) {
                        nrb-=dr[d]; ncb-=dc[d];
                    } else {
                        nrr-=dr[d]; ncr-=dc[d];
                    }
                }

                if (!vis[nrb][ncb][nrr][ncr]) {
                    vis[nrb][ncb][nrr][ncr] = true;
                    que.offer(new int [] {nrr, ncr, nrb, ncb, cnt+1});
                }
            }
        }

        return -1;
    }
}