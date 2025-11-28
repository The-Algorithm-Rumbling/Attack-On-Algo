import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, num;
    static String s;
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken();
            if (s.equals("add")) {
                set.add(Integer.parseInt(st.nextToken()));
            } else if (s.equals("remove")) {
                set.remove(Integer.parseInt(st.nextToken()));
            } else if (s.equals("check")) {
                num = Integer.parseInt(st.nextToken());
                if (set.contains(num)) sb.append("1\n");
                else sb.append("0\n");
            } else if (s.equals("toggle")) {
                num = Integer.parseInt(st.nextToken());
                if (set.contains(num)) set.remove(num);
                else set.add(num);
            } else if (s.equals("all")) {
                set.clear();
                for (int j = 1; j < 21; j++) set.add(j);
            } else {
                set.clear();
            }
        }
        
        System.out.println(sb.toString());
    }
}