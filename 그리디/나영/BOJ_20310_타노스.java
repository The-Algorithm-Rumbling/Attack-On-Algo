import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s;
    static boolean [] vis;
    static int cnt1, cnt0, ans1, ans0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        s = br.readLine();
        vis = new boolean [s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') cnt1++;
            else cnt0++;
        }

        cnt1 /= 2;
        cnt0 /= 2;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ans1++;
                vis[i] = true;
            }
            if (cnt1 == ans1) {
                break;
            }
        }

        for (int i = s.length()-1; i > -1; i--) {
            if (s.charAt(i) == '0') {
                ans0++;
                vis[i] = true;
            }
            if (cnt0 == ans0) {
                break;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (vis[i]) continue;
            sb.append(s.charAt(i));
        }
        
        System.out.println(sb.toString());
    }
}