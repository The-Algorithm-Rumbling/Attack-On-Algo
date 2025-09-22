import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> subway [];
    static StringTokenizer st;
    static int [] dist;
    static int n, end, cnt;
    static boolean [] vis, cycle;
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        subway = new ArrayList [n+1];
        vis = new boolean [n+1];
        cycle = new boolean [n+1];
        dist = new int [n+1];

        for (int i = 1; i < n+1; i++) {
            subway[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            subway[a].add(b);
            subway[b].add(a);
        }

        dfs(1, 0);

        Arrays.fill(dist, -1);

        for (int i = 1; i < n+1; i++) {
            if (cycle[i]) {
                dist[i] = 0;
                que.offer(i);
            }
        }

        while(!que.isEmpty()) {
            int q = que.poll();

            for (int i : subway[q]) {
                if (dist[i] == -1) {
                    dist[i] = dist[q]+1;
                    que.offer(i);
                }
            }
        }

        for (int i = 1; i < n+1; i++) {
            sb.append(dist[i]).append(" ");
        }
        
        System.out.print(sb.toString());
    }

    static int dfs(int tmp, int prev) {
        vis[tmp] = true;
        
        for (int i : subway[tmp]) {
            if (!vis[i]) {
                int num = dfs(i, tmp);

                if (num > 0) {
                    cycle[tmp] = true;
                    if (tmp == num) return 0;
                    return num;
                }
            } else if (i != prev) {
                cycle[tmp] = true;
                return i;
            }
        }

        return 0;
    }
}