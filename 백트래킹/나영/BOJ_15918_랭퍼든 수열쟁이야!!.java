import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner (System.in);
    static Set<String> set = new HashSet<>();
    static boolean [] vis, used;
    static int n, x, y;
    static int [] arr;
    public static void main(String[] args) {
        n = sc.nextInt();
        x = sc.nextInt()-1;
        y = sc.nextInt()-1;

        arr = new int [n*2];
        vis = new boolean [n*2];
        used = new boolean [n+1];

        for (int i = 1; i <= n; i++) {
            if (y-x != i+1) continue;
            vis[x] = vis[y] = true;
            arr[x] = arr[y] = i;
            used[i] = true;

            dfs(0);
            break;
        }
        System.out.println(set.size());
    }

    static void dfs (int idx) {
        if (idx == n*2) {
            String s = "";
            for (int i : arr) s = s + i;
            set.add(s);
            return;
        }

        if (vis[idx]) {
            dfs(idx+1);
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            if (!used[i] && idx + i + 1 < n*2 && !vis[idx+i+1]) {
                vis[idx] = true;
                vis[idx+i+1] = true;
                arr[idx] = i;
                arr[idx+i+1] = i;
                used[i] = true;

                dfs(idx+1);
                
                vis[idx] = false;
                vis[idx+i+1] = false;
                used[i] = false;
            }
        }
    }
}