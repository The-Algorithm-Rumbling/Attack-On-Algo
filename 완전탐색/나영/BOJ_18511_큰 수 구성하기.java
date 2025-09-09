import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [] arr;
    static int n, k, ans;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int [k];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 0);
        
        System.out.println(ans);
    }

    static void dfs(int sum, int depth) {
        if (sum > n) return;

        ans = Math.max(ans, sum);
        
        for (int i = k-1; i >= 0; i--) {
            dfs(sum * 10 + arr[i], depth+1);
        }
    }
}