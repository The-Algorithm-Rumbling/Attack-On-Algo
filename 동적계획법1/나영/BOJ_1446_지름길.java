import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, d;
    static int [] dp;
    static List<int []> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        dp = new int [d+1];

        for (int i = 0; i <= d; i++) dp[i] = i;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (e > d || dist >= e - s) continue;
            
            list.add(new int[] {s, e, dist});
        }

        for (int i = 2; i <= d; i++) {
            dp[i] = dp[i-1] + 1;
            for (int [] edge : list) {
                if (edge[1] == i) {
                    dp[i] = Math.min(dp[i], dp[edge[0]] + edge[2]);
                }
            }
        }
        
        System.out.println(dp[d]);
    }
}