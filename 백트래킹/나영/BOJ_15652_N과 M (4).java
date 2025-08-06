import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        dfs(1, 0, new int[m]);
        
        System.out.println(sb.toString());
    }

    static void dfs(int start, int cnt, int[] p) {
        if (cnt == m) {
            for (int i : p) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = start; i < n+1; i++) {
            p[cnt] = i;
            dfs(i, cnt+1, p);
        }
    }
}