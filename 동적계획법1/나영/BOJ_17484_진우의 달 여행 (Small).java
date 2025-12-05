import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans;
    static int [][] map;
    static int [][][] dp;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];
        dp = new int [n][m][3];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[r][c], Integer.MAX_VALUE);
            }
        }

        for (int c = 0; c < m; c++) {
            for (int d = 0; d < 3; d++) {
                dp[0][c][d] = map[0][c];
            }
        }

        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // 왼쪽 위에서 옴
                if (c < m-1) {
                    dp[r][c][0] = Math.min(dp[r-1][c+1][1], dp[r-1][c+1][2]) + map[r][c];
                }

                // 바로 위에서 옴
                dp[r][c][1] = Math.min(dp[r-1][c][0], dp[r-1][c][2]) + map[r][c];

                // 오른쪽 위에서 옴
                if (c > 0) {
                    dp[r][c][2] = Math.min(dp[r-1][c-1][0], dp[r-1][c-1][1]) + map[r][c];
                }
            }
        }

        ans = Integer.MAX_VALUE;
        for (int c = 0; c < m; c++) {
            for (int d = 0; d < 3; d++) {
                ans = Math.min(ans, dp[n-1][c][d]);
            }
        }

        System.out.println(ans);
        
    }
}