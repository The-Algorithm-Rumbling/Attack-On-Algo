import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h, x, y, ans;
    static int [][] map;
    static List<int[]> mint = new ArrayList<>();
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int [n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    x = r;
                    y = c;
                } else if (map[r][c] == 2) {
                    mint.add(new int[] {r, c});
                }
            }
        }

        visited = new boolean [mint.size()];
        dfs(x, y, m, 0);

        System.out.println(ans);
    }

    static void dfs(int r, int c, int hp, int cnt) {
        for (int i = 0; i < mint.size(); i++) {
            if (!visited[i]) {
                int temp = Math.abs(mint.get(i)[0] - r) + Math.abs(mint.get(i)[1] - c);
                if (hp >= temp) {
                    visited[i] = true;
                    dfs(mint.get(i)[0], mint.get(i)[1], hp - temp + h, cnt + 1);
                    visited[i] = false;
                }
            }
        }
        
        // 집으로 돌아갈 수 있는 체력이 남아있으면 ans 갱신
        int homeDist = Math.abs(r - x) + Math.abs(c - y);
        if (hp >= homeDist) {
            ans = Math.max(ans, cnt);
        }
    }
}