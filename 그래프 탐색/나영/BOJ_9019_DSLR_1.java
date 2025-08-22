import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, x, y;
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();
    static class P {
        int a;
        String ans;

        P(int a, String ans) {
            this.a = a;
            this.ans = ans;
        }
    }
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            visited = new boolean [10000];
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            visited[x] = true;

            bfs();
        }
        
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<P> que = new LinkedList<>();
        que.offer(new P (x, ""));

        while (!que.isEmpty()) {
            P p = que.poll();

            if (p.a == y) {
                sb.append(p.ans).append("\n");
                return;
            }

            int n = p.a;

            int nr = n*2 % 10000;
            if (!visited[nr]) {
                que.offer(new P (nr, p.ans + "D"));
                visited[nr] = true;
            }

            nr = n - 1 < 0 ? 9999 : n - 1;
            if (!visited[nr]) {
                que.offer(new P (nr, p.ans + "S"));
                visited[nr] = true;
            }

            int n4 = n / 1000;
            nr = (n % 1000) * 10 + n4;
            if (!visited[nr]) {
                que.offer(new P (nr, p.ans + "L"));
                visited[nr] = true;
            }

            int n1 = n % 10;
            int nn = n / 10;
            nr = nn + n1 * 1000;
            if (!visited[nr]) {
                que.offer(new P (nr, p.ans + "R"));
                visited[nr] = true;
            }

        }
    }
}