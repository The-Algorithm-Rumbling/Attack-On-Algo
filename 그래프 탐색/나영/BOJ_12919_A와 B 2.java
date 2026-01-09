import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s, e, reverse;
    public static void main(String[] args) throws IOException {
        s = br.readLine();
        e = br.readLine();

        if (dfs(e)) System.out.println(1);
        else System.out.println(0);
    }

    static boolean dfs(String str) {
        if (str.length() == s.length()) {
            if (str.equals(s)) return true;
            return false;
        }

        if (str.charAt(str.length()-1) == 'A') {
            if (dfs(str.substring(0, str.length()-1))) return true;
        }


        if (str.charAt(0) == 'B') {
            reverse = new StringBuilder(str.substring(1)).reverse().toString();
            if (dfs(reverse)) return true;
        }

        return false;
    }
}