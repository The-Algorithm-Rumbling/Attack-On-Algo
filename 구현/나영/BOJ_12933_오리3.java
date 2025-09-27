import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] ch;
    static int ans;
    static String quack = "quack";
    static int [] count = new int [5];
    public static void main(String[] args) {
        ch = sc.next().toCharArray();

        for (char c : ch) {
            int idx = quack.indexOf(c);

            if (idx == 0) count[0]++;
            else {
                if (count[idx-1] == 0) {
                    System.out.println(-1);
                    return;
                }
                count[idx-1]--;
                count[idx]++;
            }

            int cur = count[0] + count[1] + count[2] + count[3];
            ans = Math.max(ans, cur);
        }

        for (int i = 0; i < 4; i++) {
            if (count[i] != 0) {
                System.out.println(-1);
                return;
            }
        }
        
        System.out.println(ans);
    }
}