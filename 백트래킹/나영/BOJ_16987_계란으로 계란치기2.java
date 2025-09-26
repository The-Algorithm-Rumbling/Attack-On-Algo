import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, ans;
    static int [][] eggs;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) {
        n = sc.nextInt();

        eggs = new int [n][2];
        
        for (int i = 0; i < n; i++) {
            eggs[i][0] = sc.nextInt();
            eggs[i][1] = sc.nextInt();
        }
        
        System.out.println(dfs(0));
    }

    static int dfs(int depth) {
        if (depth == n) {
            int sum = 0;
            for (int [] egg : eggs) if (egg[0] <= 0) sum++;
            return sum;
        }

        if (eggs[depth][0] <= 0) return dfs(depth+1);

        boolean isS = true;
        for (int i = 0; i < n; i++) {
            if (eggs[i][0] <= 0 || depth == i) continue;
            isS = false;

            eggs[i][0] -= eggs[depth][1];
            eggs[depth][0] -= eggs[i][1];

            ans = Math.max(ans, dfs(depth+1));

            eggs[i][0] += eggs[depth][1];
            eggs[depth][0] += eggs[i][1];
        }

        if (isS) ans = Math.max(ans, dfs(depth+1));
        
        return ans;
    }
}