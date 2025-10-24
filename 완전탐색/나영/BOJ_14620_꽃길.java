import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, min=Integer.MAX_VALUE;
    static boolean isS;
    static int [][] map;
    static boolean [][] vis;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n][n];
        vis = new boolean [n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, n+1, 0);
        
        System.out.println(min);
    }

    static void dfs(int depth, int idx, int sum) {
        if (depth==3) {
            min = Math.min(min, sum);
            return;
        }

        if (idx == n*n) return;

        int r = idx / n;
        int c = idx % n;

        dfs(depth, idx+1, sum);

        if (r == 0 || r == n-1 || c == 0 || c == n-1 || vis[r][c]) return;

        isS = true;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (vis[nr][nc]) {
                isS = false;
                break;
            }
        }

        // 헤당 위치에 꽃을 심을 수 있는 경우
        if (isS) {
            int sumEx = sum + map[r][c];
            vis[r][c] = true;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                sumEx += map[nr][nc];
                vis[nr][nc] = true;
            }
            
            dfs(depth+1, idx+1, sumEx);

            vis[r][c] = false;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                vis[nr][nc] = false;
            }
        }
        
    }
}