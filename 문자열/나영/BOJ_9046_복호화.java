import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] charn;
    static int T, max;
    static boolean isS;
    static char maxChar;
    static int [] cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        T = sc.nextInt();
        sc.nextLine();

        for (int t = 0; t < T; t++) {
            charn = sc.nextLine().replaceAll(" ", "").toCharArray();
            max = 0;
            isS = true;
            cnt = new int [26];

            for (int i = 0; i < charn.length; i++) {
                cnt[charn[i]-'a']++;
            }
            
            for (int i = 0; i < charn.length; i++) {
                if (cnt[charn[i]-'a'] > max) {
                    isS = true;
                    max = cnt[charn[i]-'a'];
                    maxChar = charn[i];
                } else if (maxChar != charn[i] && cnt[charn[i]-'a'] == max) isS = false;
            }

            if (isS) sb.append(maxChar).append("\n");
            else sb.append('?').append("\n");
        }
        
        System.out.println(sb.toString());
    }
}