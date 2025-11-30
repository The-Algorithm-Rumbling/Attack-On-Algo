import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k, ans;
    static char [][] arr;
    static String s;
    static boolean [] alphabet = new boolean [26];
    static char [] charn = {'a', 'n', 't', 'i', 'c'};
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new char[n][];
        sc.nextLine();

        for (char c : charn) {
            alphabet[c - 'a'] = true;
            k--;
            if (k < 0) {
                System.out.println(0);
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            s = sc.nextLine();
            if (s.length() > 8) arr[i] = s.substring(4, s.length()-4).toCharArray();
            else arr[i] = new char[0];
        }

        dfs(0, k);
        
        System.out.println(ans);
    }

    static void dfs(int idx, int letter) {
        if (letter == 0 || idx == 26) {
           int cnt = 0;
            for (char [] cs : arr) {
                boolean isS = true;
                for (char c : cs) {
                    if (!alphabet[c - 'a']) {
                        isS = false;
                        break;
                    }
                }
                if (isS) cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }

        // 방문하지 않았던 알파벳인 경우에만 방문 체크
        if (!alphabet[idx]) {
            alphabet[idx] = true;
            dfs(idx+1, letter-1);
            alphabet[idx] = false;
        }
        
        dfs(idx+1, letter);
    }
}