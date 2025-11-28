import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static boolean [] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new boolean [n+1];
        dp[1] = true;
        if (n >= 3) dp[3] = true;

        for (int i = 4; i <= n; i++) {
            dp[i] = !dp[i-1] || !dp[i-3];
        }
        
        System.out.println(dp[n] ? "SK" : "CY");
    }
}