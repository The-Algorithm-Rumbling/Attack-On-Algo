import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int horse, w, h;
    static int [][] map;
    static boolean [][][] visited;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static int [] dr2 = {-2, -2, -1, -1, 2, 2, 1, 1};
    static int [] dc2 = {-1, 1, -2, 2, -1, 1, -2, 2};
    public static void main(String[] args) throws IOException {
        horse = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int [h][w];
        visited = new boolean [h][w][horse+1];

        for (int r = 0; r < h; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < w; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0, horse, 0});
        visited[0][0][horse] = true;

        while(!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == h-1 && q[1] == w-1) return q[3];

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][q[2]]) {
                    visited[nr][nc][q[2]] = true;
                    que.offer(new int[] {nr, nc, q[2], q[3] + 1});
                }
            }

            if (q[2] > 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = q[0] + dr2[d];
                    int nc = q[1] + dc2[d];
    
                    if (check(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][q[2]-1]) {
                        visited[nr][nc][q[2]-1] = true;
                        que.offer(new int[] {nr, nc, q[2]-1, q[3] + 1});
                    }
                }
            }
        }

        return -1;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
    }
}