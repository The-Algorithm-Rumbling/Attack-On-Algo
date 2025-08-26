import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int xs, ys, xe, ye;
    static long ans;
    static int [][][] tel = new int [3][2][2];
    static boolean visited [] = new boolean [3];
    public static void main(String[] args) {
        xs = sc.nextInt();
        ys = sc.nextInt();
        xe = sc.nextInt();
        ye = sc.nextInt();

        ans = Math.abs(xs - xe) + Math.abs(ys - ye);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                tel[i][j][0] = sc.nextInt();
                tel[i][j][1] = sc.nextInt();
            }
        }

        dfs(0, xs, ys);
        
        System.out.println(ans);
    }

    static void dfs(long sum, int x, int y) {
        ans = Math.min(sum + Math.abs(x - xe) + Math.abs(y - ye), ans);

        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            for (int j = 0; j < 2; j++) {
                int dist1 = Math.abs(tel[i][j][0] - x) + Math.abs(tel[i][j][1] - y);
                int nextX = tel[i][Math.abs(j-1)][0];
                int nextY = tel[i][Math.abs(j-1)][1];
                dfs(sum + dist1 + 10, nextX, nextY);
            }
            visited[i] = false;
        }
    }

    static void find(int num) {
        
    }
}