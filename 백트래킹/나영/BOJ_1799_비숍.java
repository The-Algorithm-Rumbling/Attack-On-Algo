import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, ans;
    static int [][] map;
    static boolean [] leftVis, rightVis;
    static List<int[]> white = new ArrayList<>();
    static List<int[]> black = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n][n];
        leftVis = new boolean [2*n];
        rightVis = new boolean [2*n];
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    if ((r + c) % 2 == 0) white.add(new int [] {r, c});
                    else black.add(new int [] {r, c});
                }
            }
        }

        ans = dfs(white, 0) + dfs(black, 0);
        
        System.out.println(ans);
    }

    static int dfs(List<int []> list, int idx) {
        if (idx == list.size()) return 0;

        int r = list.get(idx)[0];
        int c = list.get(idx)[1];
        int max = 0;

        // 이전 값에 의해 대각선 부분이 true 처리 되지 않았다면
        if (!leftVis[r+c] && !rightVis[r-c+(n-1)]) {
            leftVis[r+c] = rightVis[r-c+(n-1)] = true;
            max = Math.max(max, 1 + dfs(list, idx + 1));
            leftVis[r+c] = rightVis[r-c+(n-1)] = false;
        }
        
        max = Math.max(max, dfs(list, idx + 1));

        return max;
    }
}