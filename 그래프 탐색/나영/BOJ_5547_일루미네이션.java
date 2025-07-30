import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int w, h;
    static int [][] map;
    static boolean [][] visited;
    static int [] dr1 = {-1, -1, 1, 1, 0, 0};
    static int [] dc1 = {0, 1, 0, 1, -1, 1};
    static int [] dr2 = {-1, -1, 1, 1, 0, 0};
    static int [] dc2 = {-1, 0, -1, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int [h+2][w+2];
        visited = new boolean [h+2][w+2];

        for (int r = 1; r < h+1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < w+1; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0});
        visited[0][0] = true;
        int cnt = 0;

        while (!que.isEmpty()) {
            int[] q = que.poll();

            if (q[0] % 2 == 0) {
                for (int d = 0; d < 6; d++) {
                    int nr = q[0] + dr2[d];
                    int nc = q[1] + dc2[d];

                    if (check(nr, nc) && !visited[nr][nc]) {
                        if (map[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            que.offer(new int[] {nr, nc});
                        } else cnt++;
                    }
                }
            } else {
                for (int d = 0; d < 6; d++) {
                    int nr = q[0] + dr1[d];
                    int nc = q[1] + dc1[d];

                    if (check(nr, nc) && !visited[nr][nc]) {
                        if (map[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            que.offer(new int[] {nr, nc});
                        } else cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < h+2 && c >= 0 && c < w+2;
    }
}