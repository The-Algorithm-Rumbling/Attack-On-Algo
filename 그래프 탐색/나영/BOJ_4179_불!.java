import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans;
    static char[][] map;
    static Queue<int []> que1 = new LinkedList<>();
    static Queue<int []> que2 = new LinkedList<>();
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char [n][m];

        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 'J') que1.offer(new int [] {r, c, 0});
                else if (map[r][c] == 'F') que2.offer(new int [] {r, c});
            }
        }

        ans = bfs();
        System.out.println(ans == 0 ? "IMPOSSIBLE" : ans);
    }

    static int bfs() {
        while(!que1.isEmpty()) {
            int size2 = que2.size();
    
            while (size2 > 0) {
                int [] q = que2.poll();
    
                for(int d = 0; d < 4; d++) {
                    int nr = q[0] + dr[d];
                    int nc = q[1] + dc[d];
    
                    if (check(nr, nc) && map[nr][nc] != '#' && map[nr][nc] != 'F') {
                        map[nr][nc] = 'F';
                        que2.offer(new int [] {nr, nc});
                    }
                }
                size2--;
            }
            
            int size1 = que1.size();

            while (size1 > 0) {
                int [] q = que1.poll();
                
                if (q[0] == 0 || q[0] == n-1 || q[1] == 0 || q[1] == m-1) {
                    return q[2] + 1;
                }
                
                for(int d = 0; d < 4; d++) {
                    int nr = q[0] + dr[d];
                    int nc = q[1] + dc[d];
    
                    if (check(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = 'J';
                        que1.offer(new int [] {nr, nc, q[2] + 1});
                    }
                }
                size1--;
            }
        }

        return 0;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}