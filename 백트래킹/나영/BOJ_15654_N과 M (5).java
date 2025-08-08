import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int [] arr;
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int [n];
        visited = new boolean [n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        dfs(0, new int[m]);
        
        System.out.println(sb.toString());
    }

    static void dfs(int cnt, int[] p) {
        if (cnt == m) {
            for (int i : p) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            p[cnt] = arr[i];
            dfs(cnt+1, p);
            visited[i] = false;
        }
    }
}