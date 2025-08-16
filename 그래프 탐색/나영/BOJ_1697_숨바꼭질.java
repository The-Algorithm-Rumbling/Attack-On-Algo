import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k;
    static boolean isS;
    static int [] dr = {-1, 1};
    static boolean [] visited = new boolean [100001];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited[n] = true;
        
        System.out.println(find());
    }

    static int find() {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int [] {n, 0});
        while(!que.isEmpty()) {
            int [] q = que.poll();
            if (q[0] == k) return q[1];

            for (int i = -1; i < 2; i++) {
                int b = q[0];
                if (i == -1) b += q[0];
                else b += dr[i];

                if (check(b) && !visited[b]) {
                    visited[b] = true;
                    que.offer(new int [] {b, q[1] + 1});
                }
            }
        }

        return 0;
    }

    static boolean check(int r) {
        return r >= 0 && r < 100001;
    }
}