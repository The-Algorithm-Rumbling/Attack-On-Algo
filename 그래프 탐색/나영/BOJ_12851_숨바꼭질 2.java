import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k, ans=-1, cnt;
    static boolean [] visited = new boolean [100001];
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();

        bfs();
        System.out.println(ans);
        System.out.println(cnt);
    }

    static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int [] {n, 0});

        while(!que.isEmpty()) {
            int[] q = que.poll();

            if (ans != -1 && ans < q[1]) return;

            if (q[0] == k) {
                if (ans == -1) {
                    ans = q[1];
                }
                cnt++;
            }

            visited[q[0]] = true;

            int nr = q[0] * 2;

            if (nr <= 100000 && !visited[nr]) {
                que.offer(new int[] {nr, q[1]+1});
            }

            nr = q[0] + 1;

            if (nr <= 100000 && !visited[nr]) {
                que.offer(new int[] {nr, q[1]+1});
            }

            nr = q[0] - 1;

            if (nr >= 0 && !visited[nr]) {
                que.offer(new int[] {nr, q[1]+1});
            }
        }
    }
}