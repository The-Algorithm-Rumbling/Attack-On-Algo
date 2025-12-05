import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, idx;
    static char [] ch;
    static String cur;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        ch = br.readLine().toCharArray();

        for (int i = 1; ; i++) {
            cur = String.valueOf(i);
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) == ch[idx]) {
                    idx++;

                    if (idx == ch.length) {
                        System.out.println(i);
                        return;
                    }
                }
            }
        }
        
    }
}