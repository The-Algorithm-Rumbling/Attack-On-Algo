import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] map;
    static int n, k, s, x, y;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    /*
    static class Virus implements Comparable<Virus> {
        int type, r, c, t;
        public Virus (int type, int r, int c, int t) {
            this.type = type; this.r = r; this.c = c; this.t = t;
        }

        public int compareTo(Virus o) {
            if (this.t == o.t) return Integer.compare(this.type, o.type);
            return Integer.compare(this.t, o.t);
        }
    }
    
    static PriorityQueue<int[]> que2 = new PriorityQueue<>(
        new Comparator<int[]>() {
            public int compare(int [] a, int [] b) {
                if (a[3] == b[3]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[3], b[3]);
            }
        }
    );
    */
    
    static PriorityQueue<int[]> que = new PriorityQueue<>(
        (a, b) -> {
            if (a[3] == b[3]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[3], b[3]);
        }
    );
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int [n][n];
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 0) que.offer(new int [] {map[r][c], r, c, 0});
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    static int bfs() {
        while (!que.isEmpty()) {
            int [] q = que.poll();
            int virus = q[0];
            
            if (q[3] == s) break;

            for (int d = 0; d < 4; d++) {
                int nr = q[1] + dr[d];
                int nc = q[2] + dc[d];

                if (check(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = virus;
                    que.offer(new int[] {virus, nr, nc, q[3] + 1});
                }
            }
        }

        return map[x][y];
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}