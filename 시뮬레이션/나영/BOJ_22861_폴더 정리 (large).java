import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k, q, cnt;
    static Set<String> files = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    static Map<String, Set<String>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n+m; i++) {
            st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) files.add(s2);
            
            Set<String> set = new HashSet<>();
            
            if (map.containsKey(s1)) set = map.get(s1);
            set.add(s2);
            map.put(s1, set);
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            String token1 = st.nextToken();
            String token2 = st.nextToken();
            String s1; String s2;
            
            if (token1.contains("/")) {
                String [] ss = token1.split("/");
                s1 = ss[ss.length-1];
            } else s1 = token1;
            
            if (token2.contains("/")) {
                String [] ss = token2.split("/");
                s2 = ss[ss.length-1];
            } else s2 = token2;

            Set<String> set1 = map.getOrDefault(s1, new HashSet<>());
            Set<String> set2 = map.getOrDefault(s2, new HashSet<>());

            map.remove(s1);

            for (String ss : set1) {
                set2.add(ss);
            }

            map.put(s2, set2);
        }

        q = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String [] s = st.nextToken().split("/");
            
            Set<String> set1 = map.getOrDefault(s[s.length - 1], new HashSet<>());
            Set<String> set2 = new HashSet<>();
            cnt = 0;

            find(set1, set2);

            sb.append(set2.size()).append(" ").append(cnt).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static void find(Set<String> set1, Set<String> set2) {
        for (String s : set1) {
            if (map.containsKey(s)) {
                find(map.get(s), set2);
            } else if (files.contains(s)) {
                set2.add(s);
                cnt++;
            }
        }
    }
    
}