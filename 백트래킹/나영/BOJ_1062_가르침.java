import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k, ans;
    static char [][] arr;
    static boolean [] visited;
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();

        visited = new boolean [26];
        arr = new char [n][];

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            if (s.length() > 8) arr[i] = s.substring(4, s.length() - 4).toCharArray();
            else arr[i] = new char[0];
        }

        check('a');
        check('c');
        check('n');
        check('i');
        check('t');

        if (k < 0) {
            System.out.println(ans);
            return;
        } else if (k >= 21) {
            System.out.println(n);
            return;
        }
        
        dfs(0, k);
        System.out.println(ans);
    }
    
    static void dfs(int start, int depth) {
        if (depth == 0) {
            ans = Math.max(ans, countWords());
            return;
        }

        for(int i = start; i < 26; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i+1, depth-1);
            visited[i] = false;
        }
    }

    static int countWords() {
        int words = 0;
        for (char [] charn : arr) {
            boolean isS = true;
            for (char c : charn) {
                if(!visited[c-'a']) {
                    isS = false;
                    break;
                }
            }
            if (isS) words++;
        }
        return words;
    }

    static void check(char c) {
        visited[c - 'a'] = true;
        k--;
    }
}