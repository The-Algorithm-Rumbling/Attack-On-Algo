import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ans;
    static char[] charn;
    static int [] cnt;
    static List<Character> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        charn = br.readLine().toCharArray();
        cnt = new int [26];

        for (char c : charn) {
            cnt[c - 'a']++;
        }

        find(-1, 0);
        
        System.out.println(ans);
    }

    static void find(int prev, int depth) {
        if (depth == charn.length) {
            ans++;
            return;
        }
        
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0 || prev == i) continue;
            cnt[i]--;
            find(i, depth + 1);
            cnt[i]++;
        }
    }
}