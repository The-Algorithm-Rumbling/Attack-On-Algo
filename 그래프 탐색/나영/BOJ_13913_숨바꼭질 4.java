import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k;
    static int prev [] = new int [100001];
    static boolean visited [] = new boolean [100001];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();

        bfs();
        
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int [] {n, 0});
        visited[n] = true;

        while (!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == k) {
                sb.append(q[1]).append("\n");
                find(q[0]);
                return;
            }

            int nr = q[0] * 2;
            if (nr <= 100000 && !visited[nr]) {
                visited[nr] = true;
                prev[nr] = q[0];
                que.offer(new int [] {nr, q[1] + 1});
            }

            nr = q[0] + 1;
            if (nr <= 100000 && !visited[nr]) {
                visited[nr] = true;
                prev[nr] = q[0];
                que.offer(new int [] {nr, q[1] + 1});
            }

            nr = q[0] - 1;
            if (nr >= 0 && !visited[nr]) {
                visited[nr] = true;
                prev[nr] = q[0];
                que.offer(new int [] {nr, q[1] + 1});
            }
        }
    }

    static void find(int num) {
        List<Integer> list = new ArrayList<>();
        list.add(num);
        while (num != n) {
            list.add(prev[num]);
            num = prev[num];
        }

        for (int i = list.size()-1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }
    }
}