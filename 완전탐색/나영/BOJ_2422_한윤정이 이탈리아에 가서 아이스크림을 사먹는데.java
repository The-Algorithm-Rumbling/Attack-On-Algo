import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, cnt;
    static boolean [][] ice;
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        ice = new boolean [n+1][n+1];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            ice[a][b] = ice[b][a] = true;
        }

        dfs(0, 1, new int [3]);
        System.out.println(cnt);
    }

    static void dfs (int depth, int start, int [] arr) {
        if (depth == 3) {
            if (!check(arr)) return;
            cnt++;
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            dfs(depth+1, i+1, arr);
        }
    }

    static boolean check(int [] arr) {
        return !(ice[arr[0]][arr[1]] || ice[arr[0]][arr[2]] || ice[arr[1]][arr[2]]);
    }
}