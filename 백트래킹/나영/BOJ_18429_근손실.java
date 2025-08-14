import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, ans;
    static int [] arr;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int [n];
        visited = new boolean [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0, 500, 0);
        
        System.out.println(ans);
    }

    static void dfs(int start, int sum, int cnt) {
        if (cnt == n) {
            if (sum >= 500) ans++;
            return;
        }

        sum -= k;

        for (int i = 0; i < n; i++) {
            if (visited[i] || sum + arr[i] < 500) continue;
            visited[i] = true;
            dfs(i, sum + arr[i], cnt + 1);
            visited[i] = false;
        }
    }
}