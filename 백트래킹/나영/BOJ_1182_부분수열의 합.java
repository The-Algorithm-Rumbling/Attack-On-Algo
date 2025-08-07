import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, s, ans;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        
        System.out.println(ans);
    }

    static void dfs(int start, int sum) {
        if (start > 0 && sum == s) ans++;
        for (int i = start; i < n; i++) {
            dfs(i+1, sum + arr[i]);
        }
    }
}