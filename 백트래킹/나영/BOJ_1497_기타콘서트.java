import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, max, ans;
    static List<Integer> [] guitar;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        guitar = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            guitar[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            st.nextToken();
            char [] charn = st.nextToken().toCharArray();

            for (int j = 0; j < m; j++) {
                if (charn[j] == 'Y') guitar[i].add(j);
            }
        }

        find(0, 0, new HashSet<>());
        
        System.out.println(ans!=0 ? ans : -1);
    }

    static void find(int start, int cnt, Set<Integer> set) {
        if (max < set.size()) {
            max = set.size();
            ans = cnt;
        } else if (max == set.size()) ans = Math.min(ans, cnt);
        
        if (start == n) return;

        for (int i = start; i < n; i++) {
            List<Integer> notes = new ArrayList<>();
            for (int j : guitar[i]) {
                if (set.contains(j)) continue;
                set.add(j);
                notes.add(j);
            }
            
            if (!notes.isEmpty()) find(i+1, cnt+1, set);

            for (int note : notes) set.remove(note);
        }
    }
}