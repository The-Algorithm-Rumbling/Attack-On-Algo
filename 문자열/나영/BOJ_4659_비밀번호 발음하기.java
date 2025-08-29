import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s;
    static Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        while (true) {
            s = br.readLine();
            if (s.equals("end")) break;
            sb.append('<').append(s).append("> ");

            if (dfs(0, 0, ' ', false)) sb.append("is acceptable.");
            else sb.append("is not acceptable.");

            sb.append("\n");
            
        }
        
        System.out.println(sb.toString());
    }

    static boolean dfs(int idx, int cnt, char c, boolean isS) {

        if (cnt == 3) {
            return false;
        }
        
        if (idx == s.length()) {
            if (isS) return true;
            return false;
        }
        
        char tmp = s.charAt(idx);

        if ((set.contains(tmp) && set.contains(c)) || (!set.contains(tmp) && !set.contains(c))) cnt++;
        else cnt = 1;

        if (set.contains(tmp)) isS = true;
        
        if (tmp == c && tmp != 'e' && tmp != 'o') return false;

        return dfs(idx + 1, cnt, tmp, isS);
    }
}