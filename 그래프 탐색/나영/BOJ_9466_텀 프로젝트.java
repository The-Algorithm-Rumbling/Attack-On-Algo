import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, n, ans, cnt;
    static int[] arr;
    static List<Integer> list;
    static boolean [] visited, finish;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            list = new ArrayList<>();
            visited = new boolean[n+1];
            finish = new boolean[n+1];
            ans = 0;
            
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i < n+1; i++) {
                int a = Integer.parseInt(st.nextToken());
                arr[i] = a;
            }

            for (int i = 1; i < n+1; i++) {
                if(!visited[i]) dfs(i);
            }

            sb.append(n - ans).append("\n");
            
        }
        
        System.out.println(sb.toString());
    }

    static void dfs(int a) {
        visited[a] = true;
        
        if (!visited[arr[a]]) {
            dfs(arr[a]);
        } else if (!finish[arr[a]]) {
            ans ++;

            for (int i = arr[a]; i != a; i = arr[i]) {
                ans ++;
            }
        } 
        finish[a] = true;
        
    }
}