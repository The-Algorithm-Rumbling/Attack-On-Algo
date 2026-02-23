import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int [] arr;
    static boolean [] vis;
    static Set<Integer> set = new TreeSet<>();
    public static void main(String[] args) {
        n = sc.nextInt();

        arr = new int [n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            vis = new boolean [n+1];
            dfs(i, i);
        }
        
        System.out.println(set.size());
        for (int num : set) {
            System.out.println(num);
        }
    }

    static void dfs(int start, int tmp) {
        if (!vis[tmp]) {
            vis[tmp] = true;
            dfs(start, arr[tmp]);
        } else if (tmp == start) set.add(start);
    }
}