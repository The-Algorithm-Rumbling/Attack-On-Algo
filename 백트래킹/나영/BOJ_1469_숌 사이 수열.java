import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int [] arr, ex;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int [n];
        ex = new int [2*n];
        Arrays.fill(ex, -1);
        visited = new boolean [n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > 2 * n - 2) {
                System.out.println(-1);
                return;
            }
        }

        Arrays.sort(arr);

        dfs (0);
        System.out.println(-1);
    }

    static void dfs(int depth) {
        if (depth == 2*n) {
            for (int i : ex) System.out.print(i + " ");
            System.exit(0);
            return;
        }

        if (ex[depth] != -1) {
            dfs(depth+1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int j = depth + arr[i] + 1;
            if (j < 2*n && ex[j] == -1) {
                visited[i] = true;
                ex[depth] = ex[j] = arr[i];
                dfs(depth+1);
                ex[depth] = ex[j] = -1;
                visited[i] = false;
            }
        }
    }
}