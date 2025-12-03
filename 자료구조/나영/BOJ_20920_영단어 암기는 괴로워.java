import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static String s;
    static List<String> list;
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            s = br.readLine();
            if (s.length() < m) continue;
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        list = new ArrayList<>(map.keySet());

        list.sort((a, b) -> {
            if (map.get(a) != map.get(b)) return map.get(b) - map.get(a);
            if (a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });

        for (String ss : list) sb.append(ss).append("\n");
        
        System.out.println(sb.toString());
    }
}