import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String [] ss;
    static int n, m;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) set.add(br.readLine());

        for (int i = 0; i < m; i++) {
            ss = br.readLine().split(",");

            for (String s : ss) {
                if (set.contains(s)) {
                    set.remove(s);
                }
            }

            sb.append(set.size()).append("\n");
        }

        System.out.println(sb.toString());
        
    }
}