import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, ans;
    static int [] d, w;
    // static boolean [] visited;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        d = new int [n];
        w = new int [n];
        // visited = new boolean [n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a가 내구도, b가 무게
            d[i] = a;
            w[i] = b;
        }
        
        System.out.println(dfs(0, d));
    }

    static int dfs(int cnt, int [] eggs) {
        if (cnt == n) {
            int sum = 0;
            for (int e : eggs) {
                if (e <= 0) sum++;
            }
            return sum;
        }

        if (eggs[cnt] <= 0) return dfs(cnt + 1, eggs);

        boolean isS = false;
        
        for (int i = 0; i < n; i++) {
            if(eggs[i] <= 0 || cnt == i) continue;
            isS = true;
            int [] ex = eggs.clone();

            ex[cnt] -= w[i];
            ex[i] -= w[cnt];
            
            ans = Math.max(dfs(cnt + 1, ex), ans);
        }

        if (!isS) ans = Math.max(dfs(cnt + 1, eggs), ans);

        return ans;
    }
}