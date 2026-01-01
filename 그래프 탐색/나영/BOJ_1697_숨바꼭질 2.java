import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k;
    static boolean [] vis;
    public static void main(String[] args) {
        n = sc.nextInt(); k = sc.nextInt();
        
        System.out.println(bfs());
    }

    static int bfs() {
        vis = new boolean[100001];
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {n, 0});
        vis[n] = true;

        while (!que.isEmpty()) {
            int [] q = que.poll();

            int tmp = q[0]; int cnt = q[1];

            if (tmp == k) return cnt;

            int next = tmp * 2;
            if (next < vis.length && !vis[next]) {
                vis[next] = true;
                que.offer(new int [] {next, cnt+1});
            }

            next = tmp + 1;
            if (next < vis.length && !vis[next])  {
                vis[next] = true;
                que.offer(new int [] {next, cnt+1});
            }

            next = tmp - 1;
            if (next >= 0 && !vis[next]) {
                vis[next] = true;
                que.offer(new int [] {next, cnt+1});
            }
        }

        return 0;
    }
}