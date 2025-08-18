import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, ans;
    static boolean [] deleted;
    static List<Integer> [] list;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        list = new ArrayList [n];
        deleted = new boolean [n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a >= 0) list[a].add(i);
        }
        
        k = Integer.parseInt(br.readLine());

        dfs(k);

        for (int i = 0; i < n; i++) {
            if (deleted[i]) continue;
            boolean isS = true;
            for (int j : list[i]) {
                if (!deleted[j]) isS = false;
            }
            if (isS) ans++;
        }
        
        System.out.println(ans);
    }

    static void dfs (int idx) {
        deleted[idx] = true;
        for (int i : list[idx]) {
            dfs(i);
        }
    }
}