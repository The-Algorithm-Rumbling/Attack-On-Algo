import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] charn;
    static int n, min=Integer.MAX_VALUE;
    static int [] money, count = new int [26];
    static int [][] names;
    public static void main(String[] args) {
        charn = sc.next().toCharArray();

        for (char c : charn) {
            count[c - 'A']++;
        }
        
        n  = sc.nextInt();

        names = new int [n][26];
        money = new int [n];

        for (int i = 0; i < n; i++) {
            money[i] = sc.nextInt();
            charn = sc.next().toCharArray();

            for (char c : charn) {
                names[i][c - 'A']++;
            }
        }

        dfs(0, 0);
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs (int start, int sum) {
        if (start == n) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) return;
            }

            min = Math.min(min, sum);
            return;
        }

        dfs(start+1, sum);

        int [] backUp = Arrays.copyOf(count, 26);
        for (int i = 0; i < 26; i++) {
            count[i] -= names[start][i];
            if (count[i] < 0) count[i] = 0;
        }
        
        dfs(start+1, sum + money[start]);

        count = backUp;
    }
}