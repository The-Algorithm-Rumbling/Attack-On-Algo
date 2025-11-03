import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k;
    static int [] arr;
    static boolean [] vis;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int [n];
        vis = new boolean [n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        dfs(0, new StringBuilder());
        
        System.out.println(set.size());
    }

    static void dfs (int depth, StringBuilder sb) {
        if (depth == k) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            int len = sb.length();
            sb.append(arr[i]);
            
            dfs(depth+1, sb);

            int len2 = sb.length();
            vis[i] = false;
            for (int l = len; l < len2; l++) {
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}