import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static char [] arr;
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            arr = br.readLine().toCharArray();
            visited = new boolean [arr.length];
            Arrays.sort(arr);
            dfs(0, new int [arr.length]);
        }
        
        System.out.println(sb.toString());
    }

    static void dfs(int cnt, int [] perm) {
        if (cnt == perm.length) {
            for (int i : perm) sb.append(arr[i]);
            sb.append("\n");
            return;
        }

        char pre = 'A';
        for (int i = 0; i < perm.length; i++) {
            if (visited[i] || pre == arr[i]) continue;
            pre = arr[i];
            visited[i] = true;
            perm[cnt] = i;
            dfs(cnt+1, perm);
            visited[i] = false;
        }
    }
}