import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean [] vis;
    static int n, m, x, cnt;
    static List<Integer> [] up, down;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        up = new ArrayList[n+1];
        down = new ArrayList[n+1];
        vis = new boolean[n+1];
        for (int i = 1; i < n+1; i++) {
            up[i] = new ArrayList<>();
            down[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            up[b].add(a);
            down[a].add(b);
        }

        dfs(x, up);
        System.out.print(cnt + " ");
        
        vis = new boolean[n+1];
        cnt = 0;
        dfs(x, down);
        System.out.print(n - (cnt-1));
        
    }

    static void dfs(int tmp, List<Integer> [] list) {
        cnt++;
        vis[tmp] = true;

        for (int i : list[tmp]) {
            if(vis[i]) continue;
            dfs(i, list);
        }
    }
}