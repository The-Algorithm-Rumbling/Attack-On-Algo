import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max;
    static List<int []> arr[];
    static boolean visited [];
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean [n+1];

        arr = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new int [] {b, c});
            arr[b].add(new int [] {a, c});
        }

        for (int i = 1; i < arr.length; i++) {
            dfs(i, 0);
        }
        
        System.out.println(max);
    }

    static void dfs(int start, int sum) {
        visited[start] = true;
        max = Math.max(max, sum);
        for (int [] p : arr[start]) {
            if(!visited[p[0]]) dfs(p[0], sum + p[1]);
        }
        visited[start] = false;
    }
}