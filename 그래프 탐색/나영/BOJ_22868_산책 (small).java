import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean isS;
    static boolean [] visited;
    static int n, m, s, e, ans;
    static List<Integer> [] list;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }
        
        bfs(s, e);
        bfs(e, s);
        
        System.out.println(ans);
    }

    static void bfs(int a, int b) {
        Queue<List<Integer>> que = new LinkedList<>();
        List<Integer> ex = new ArrayList<>();
        ex.add(a);
        que.offer(ex);

        while(!que.isEmpty()) {
            List<Integer> q = que.poll();

            if (q.get(q.size()-1) == b) {
                ans += q.size()-1;
                if (b == s) return;
                visited = new boolean[n+1];
                for (int i : q) {
                    visited[i] = true;
                }
                visited[a] = false;
                return;
            }

            for (int i : list[q.get(q.size()-1)]) {
                if(visited[i]) continue;
                visited[i] = true;
                List<Integer> ex2 = new ArrayList<>(q);
                ex2.add(i);
                que.offer(ex2);
            }
        }
    }
}