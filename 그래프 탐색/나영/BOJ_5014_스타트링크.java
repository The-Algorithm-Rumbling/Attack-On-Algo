import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int F, S, G, U, D, ans;
    static boolean [] visited;
    public static void main(String[] args) {
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();

        visited = new boolean [F+1];

        ans = bfs();
        
        System.out.println(ans != -1 ? ans : "use the stairs");
    }

    static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int [] {S, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == G) return q[1];

            int nr = q[0] + U;

            if (nr <= F && !visited[nr]) {
                visited[nr] = true;
                que.offer(new int [] {nr, q[1] + 1});
            }

            nr = q[0] - D;

            if (nr > 0 && !visited[nr]) {
                visited[nr] = true;
                que.offer(new int [] {nr, q[1] + 1});
            }
        }

        return -1;
    }
}