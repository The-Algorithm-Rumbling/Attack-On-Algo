import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, max;
    static int [][] chicken;
    static boolean [] vis;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        chicken = new int [n][m];
        vis = new boolean [n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                chicken[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0, new int [3]);
        
        System.out.println(max);
    }

    static void dfs (int depth, int start, int [] arr) {
        if (depth == 3) {
            max = Math.max(max, getSum(arr));
            return;
        }

        for (int i = start; i < m; i++) {
            arr[depth] = i;
            dfs(depth+1, i+1, arr);
        }
    }

    static int getSum(int [] arr) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int maxCh = 0;
            for (int ch : arr) {
                maxCh = Math.max(maxCh, chicken[i][ch]);
            }
            sum += maxCh;
        }

        return sum;
    }
}