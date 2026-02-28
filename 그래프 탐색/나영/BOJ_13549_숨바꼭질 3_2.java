import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k, limit, ans=100001;
    static int [] vis;
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        k = sc.nextInt();
        limit = Math.max(n, k) * 2 + 1;
        vis = new int [limit];
        Arrays.fill(vis, 100001);
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {n, 0});
        vis[n] = 0;

        while(!que.isEmpty()) {
            int [] q = que.poll();

            int tmp = q[0]; int cnt = q[1];
            if (tmp == k) {
                ans = Math.min(ans, cnt);
            }

            int next = tmp * 2;

            if (next < limit && cnt < vis[next]) {
                vis[next] = cnt;
                que.offer(new int [] {next, cnt});
            }

            next = tmp + 1;

            if (next < limit && cnt+1 < vis[next]) {
                vis[next] = cnt+1;
                que.offer(new int [] {next, cnt+1});
            }

            next = tmp - 1;

            if (next >= 0 && cnt+1 < vis[next]) {
                vis[next] = cnt+1;
                que.offer(new int [] {next, cnt+1});
            }
        }

        return ans;
    }
}
