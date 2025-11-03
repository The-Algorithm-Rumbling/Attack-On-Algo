import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static char [] ch;
    static boolean [] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            ch = sc.next().toCharArray();
            Arrays.sort(ch);
            vis = new boolean [ch.length];

            dfs("");
        }
        
        System.out.println(sb.toString());
    }

    static void dfs(String s) {
        if (s.length() == ch.length) {
            sb.append(s).append("\n");
            return;
        }

        char prev = ' ';
        for (int i = 0; i < ch.length; i++) {
            if (prev == ch[i] || vis[i]) continue;
            vis[i] = true;
            prev = ch[i];

            dfs(s + ch[i]);
            
            vis[i] = false;
        }
    }
}