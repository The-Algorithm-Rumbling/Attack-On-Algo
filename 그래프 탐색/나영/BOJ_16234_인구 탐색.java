import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, l, R, ans;
    static boolean isS = true;
    static int map [][];
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int [n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while(isS) {
            isS = false;
            visited = new boolean [n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (!visited[r][c]) bfs(r, c);
                }
            }
            ans++;
        }
        
        System.out.println(ans - 1);
    }
    
    static void bfs(int r, int c) {
        Queue<int []> que = new LinkedList<>();
        List<int []> list = new ArrayList<>();
        que.offer(new int [] {r, c});
        list.add(new int [] {r, c});
        visited[r][c] = true;
        int sum = map[r][c];
        int cnt = 1;

        while (!que.isEmpty()) {
            int [] q = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && !visited[nr][nc] && Math.abs(map[q[0]][q[1]] - map[nr][nc]) >= l && Math.abs(map[q[0]][q[1]] - map[nr][nc]) <= R) {
                    visited[nr][nc] = true;
                    sum += map[nr][nc];
                    cnt++;
                    que.offer(new int [] {nr, nc});
                    list.add(new int [] {nr, nc});
                }
            }
        }

        if (cnt != 1) {
            isS = true;
            int avg = sum / cnt;
            for (int [] a : list) {
                map[a[0]][a[1]] = avg;
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

}