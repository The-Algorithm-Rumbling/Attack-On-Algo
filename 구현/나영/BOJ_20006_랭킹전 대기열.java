import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int p, m;
    static Map<P, List<P>> map = new LinkedHashMap<>();
    // static List<int []> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static class P {
        String name;
        int level;

        P(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            boolean isS = true;

            for (P p : map.keySet()) {
                if (map.get(p).size() < m && (p.level >= a-10 && p.level <= a+10)) {
                    map.get(p).add(new P(a, s));
                    isS = false;
                    break;
                }
            }

            if (isS) {
                List<P> list = new ArrayList<>();
                list.add(new P(a, s));
                map.put(new P(a, s), list);
            }
        }

        for (P key : map.keySet()) {
            if (map.get(key).size() < m) sb.append("Waiting!\n");
            else sb.append("Started!\n");

            map.get(key).sort((a, b) -> {
                return a.name.compareTo(b.name);
            });

            for (P p : map.get(key)) {
                sb.append(p.level).append(" ").append(p.name).append("\n");
            }
        }
        
        System.out.println(sb.toString());
    }
}