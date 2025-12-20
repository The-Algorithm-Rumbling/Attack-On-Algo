import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, v;
    static boolean [] vis;
    static List<Integer> [] list;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];

        for (int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i < n+1; i++) {
            list[i].sort(null);
        }

        vis = new boolean[n+1];
        dfs(v);

        sb.append("\n");
        vis = new boolean[n+1];
        bfs();
        
        System.out.println(sb);
    }

    static void dfs(int tmp) {
        if (vis[tmp]) return;
        vis[tmp] = true;
        sb.append(tmp).append(" ");

        for (int i : list[tmp]) dfs(i);
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(v);
        vis[v] = true;

        while (!que.isEmpty()) {
            int q = que.poll();
            sb.append(q).append(" ");

            for (int i : list[q]) {
                if (vis[i]) continue;
                vis[i] = true;
                que.offer(i);
            }
        }
    }
}