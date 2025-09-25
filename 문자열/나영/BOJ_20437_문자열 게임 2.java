import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, n, min, max;
    static char[] ch;
    static List<Integer> list [];
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            ch = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            list = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                list[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < ch.length; i++) {
                list[ch[i]-'a'].add(i);
            }

            min = Integer.MAX_VALUE;
            max = -1;

            for (List<Integer> l : list) {
                if (l.size() < n) continue;

                for (int i = 0; i <= l.size() - n; i++) {
                    int start = l.get(i);
                    int end = l.get(i + n - 1);
                    int len = end - start + 1;

                    min = Math.min(min, len);
                   if (ch[start] == ch[end]) max = Math.max(max, len);
                }
            }
            
            if (min != Integer.MAX_VALUE) System.out.println(min + " " + max);
            else System.out.println(-1);
        }
        
    }
}