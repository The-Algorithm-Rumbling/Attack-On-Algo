import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static String ss;
    static String [] s;
    static boolean [] visited;
    static TreeSet<String> set = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        s = new String [n];
        visited = new boolean [10];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            s[i] = st.nextToken();
        }

        for (int i = 0; i < 10; i++) {
            ss = i + "";
            visited[i] = true;
            dfs(1, ss);
            visited[i] = false;
        }

        
        System.out.println(set.last());
        System.out.println(set.first());
    }

    static void dfs (int depth, String ex) {
        if (depth == n+1) {
            set.add(ex);
            return;
        }

        String d = s[depth-1];

        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;
            if (d.equals(">")) {
                if (ex.charAt(depth-1) - '0' > i) {
                    visited[i] = true;
                    dfs(depth+1, ex + i);
                    visited[i] = false;
                }
            } else {
                if (ex.charAt(depth-1) - '0' < i) {
                    visited[i] = true;
                    dfs(depth+1, ex + i);
                    visited[i] = false;
                }
            }
        }
    }
}