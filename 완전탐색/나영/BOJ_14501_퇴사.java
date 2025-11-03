import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, max;
    static int [][] counsel;
    public static void main(String[] args) {
        
        n  = sc.nextInt();
        counsel = new int [n][2];

        for (int i = 0; i < n; i++) {
            counsel[i][0] = sc.nextInt();
            counsel[i][1] = sc.nextInt();
        }

        dfs(0, 0);
        
        System.out.println(max);
    }

    static void dfs (int day, int sum) {
        if (day == n) {
            max = Math.max(max, sum);
            return;
        }

        dfs(day+1, sum);

        int days = day + counsel[day][0];

        if (days > n) return;
        
        dfs(days, sum + counsel[day][1]);
    }
}