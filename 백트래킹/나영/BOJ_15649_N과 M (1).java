import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m;
    static int arr[];
    static boolean visited[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int [m];
        visited = new boolean[n+1];

        dfs(0);
        
        System.out.println(sb.toString());
    }

    static void dfs(int cnt) {
        if (cnt == m) {
            for (int a : arr) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        
        for (int i = 1; i < n+1; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            dfs(cnt+1);
            visited[i] = false;
        }
    }
}