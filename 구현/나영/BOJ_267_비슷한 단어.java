import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, cnt, len, ans;
    static char [] ch, charn;
    static boolean isS;
    static int [] arr1, arr2;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        charn = br.readLine().toCharArray();
        arr1 = new int [26];

        for (char c : charn) arr1[c-'A']++;

        for (int i = 0; i < n-1; i++) {
            ch = br.readLine().toCharArray();
            arr2 = new int [26];
            cnt = 0;

            for (char c : ch) {
                arr2[c-'A']++;
            }

            len = Math.abs(charn.length-ch.length);
            if (len > 1) continue;

            for (int j = 0; j < 26; j++) {
                cnt += Math.abs(arr1[j] - arr2[j]);
            }

            if (cnt <= 1) ans++;
            else if (cnt == 2 && len == 0) ans++;
        }

        System.out.println(ans);
        
    }
}