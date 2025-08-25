import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, w, h, ans;
    static char [][] map;
    static Queue<int []> que;
    static Queue<int []> fire;
    static StringBuilder sb = new StringBuilder();
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char [h][w];
            que = new LinkedList<>();
            fire = new LinkedList<>();

            for (int r = 0; r < h; r++) {
                map[r] = br.readLine().toCharArray();
                for (int c = 0; c < w; c++) {
                    if (map[r][c] == '@') que.offer(new int [] {r, c, 0});
                    else if (map[r][c] == '*') fire.offer(new int [] {r, c});
                }
            }

            ans = bfs();
            sb.append(ans != -1 ? ans : "IMPOSSIBLE").append("\n");
        }

        
        System.out.println(sb.toString());
    }

    static int bfs() {
        while (!que.isEmpty()) {
            
            int fSize = fire.size();

            while (fSize > 0) {
                int[] f = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = f[0] + dr[d];
                    int nc = f[1] + dc[d];

                    if (check(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = '*';
                        fire.offer(new int [] {nr, nc});
                    }
                }

                fSize--;
            }
            
            int qSize = que.size();

            while (qSize > 0) {
                int [] q = que.poll();
    
                if (q[0] == 0 || q[0] == h-1 || q[1] == 0 || q[1] == w-1) return q[2] + 1;
    
                for (int d = 0; d < 4; d++) {
                    int nr = q[0] + dr[d];
                    int nc = q[1] + dc[d];
    
                    if (check(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = '@';
                        que.offer(new int [] {nr, nc, q[2] + 1});
                    }
                }

                qSize--;
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
    }
}