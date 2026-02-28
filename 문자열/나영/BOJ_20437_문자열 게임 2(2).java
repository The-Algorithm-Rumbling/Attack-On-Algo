import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, k, min, max;
    static char[] ch;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            min = 10000; max = 0;
            ch = br.readLine().toCharArray();
            k = Integer.parseInt(br.readLine());
            list = new ArrayList[26];

            for (int i = 0; i < 26; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < ch.length; i++) {
                list[ch[i]-'a'].add(i);
            }

            for (List<Integer> l : list) {
                if (l.size() < k) continue;

                for (int i = 0; i <= l.size() - k; i++) {
                    int tmp = l.get(i); int next = l.get(i + k - 1);
                    min = Math.min(min, next - tmp + 1);
                    max = Math.max(max, next - tmp + 1);
                }
            }

            if (max != 0) System.out.println(min + " " + max);
            else System.out.println(-1);
        }
    }
}