import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int R, C, L, sl, sr, sc, el, er, ec, ans;
    static int map [][][];
    static boolean [][][] vis;
    static int dr [] = {-1, 0, 1, 0, 0, 0};
    static int dc [] = {0, -1, 0, 1, 0, 0};
    static int dl [] = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0) break;
            
            map = new int [L][R][C];
            vis = new boolean [L][R][C];
    
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    char [] charn = br.readLine().toCharArray();
                    for (int c = 0; c < C; c++) {
                        char ch = charn[c];
                        if (ch == 'S') {
                            sl = l; sr = r; sc = c;
                            map[l][r][c] = 1;
                        } else if (ch == 'E') {
                            el = l; er = r; ec = c;
                        } else if (ch == '.') map[l][r][c] = 0;
                        else map[l][r][c] = 1;
                    }
                }
                br.readLine();
            }
            ans = bfs();
            sb.append(ans > 0 ? String.format("Escaped in %d minute(s).\n", ans) : "Trapped!\n"); 
        }
        
        System.out.println(sb.toString());
    }

    static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{sl, sr, sc, 0});
        vis[sl][sr][sc] = true;
    
        while (!que.isEmpty()) {
            int[] q = que.poll();
            int l = q[0]; int r = q[1]; int c = q[2]; int cnt = q[3];
            if (l == el && r == er && c == ec) return cnt;
    
            for (int d = 0; d < 6; d++) {
                int nl = l + dl[d]; int nr = r + dr[d]; int nc = c + dc[d];
    
                if (check(nl, nr, nc) && map[nl][nr][nc] == 0) {
                    map[nl][nr][nc] = 1;
                    que.add(new int[]{nl, nr, nc, cnt+1});
                }
            }
        }

        return 0;
    }

    static boolean check(int l, int r, int c) {
        return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
    }
}