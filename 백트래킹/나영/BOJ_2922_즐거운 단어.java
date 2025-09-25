import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] ch;
    static boolean [] isVowel, isBlank;
    static long ans;
    static boolean isS;
    static Set<Character> vowel = new HashSet<>(Arrays.asList('A','E','I','O','U'));
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        ch = sc.next().toCharArray();
        isVowel = new boolean [ch.length];
        isBlank = new boolean [ch.length];

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '_') {
                list.add(i);
                isBlank[i] = true;
            }
            else if (vowel.contains(ch[i])) isVowel[i] = true;
            else if (!isS && ch[i] == 'L') isS = true;
        }

        dfs(0, 1);
        
        System.out.println(ans);
    }

    static void dfs(int tmp, long cnt) {
        if (tmp == list.size()) {
            if (isS) ans+=cnt;
            return;
        }

        int idx = list.get(tmp);
        isBlank[idx] = false;
        
        isVowel[idx] = true;
        if (check(idx)) dfs(tmp+1, cnt * 5);

        isVowel[idx] = false;
        if (check(idx)) {
            if (isS) dfs(tmp+1, cnt * 21);
            else {
                isS = true;
                dfs (tmp+1, cnt);
                isS = false;
                dfs (tmp+1, cnt * 20);
            }
        }

        isBlank[idx] = true;
    }

    static boolean check(int idx) {
        for (int i = idx - 2; i <= idx; i++) {
            if (i >= 0 && i + 2 < ch.length) {
                if (isBlank[i] || isBlank[i+1] || isBlank[i+2]) continue;
                if (isVowel[i] && isVowel[i+1] && isVowel[i+2]) return false;
                if (!isVowel[i] && !isVowel[i+1] && !isVowel[i+2]) return false;
            }
        }

        return true;
    }
}