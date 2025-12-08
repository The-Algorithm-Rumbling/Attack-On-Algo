import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int A, B, C, max;
    static class P {
        int a, b, c;

        P(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = Math.max(A, Math.max(B, C));

        if ((A + B + C) % 3 != 0) {
            System.out.println(0);
            return;
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        boolean [][] vis = new boolean[1501][1501];
        Queue<P> que = new LinkedList<>();
        que.offer(new P(A, B, C));
        vis[A][B] = true;

        while(!que.isEmpty()) {
            P p = que.poll();

            if (p.a == p.b && p.b == p.c) return 1;

            if (p.a > p.b) {
                // A -> B
                int a = p.a - p.b;
                int b = p.b * 2;
                if (!vis[a][b]) {
                    vis[a][b] = true;
                    que.offer(new P(a, b, p.c));
                }
            } else if (p.b > p.a) {
                // B -> A
                int b = p.b - p.a;
                int a = p.a * 2;
                if (!vis[a][b]) {
                    vis[a][b] = true;
                    que.offer(new P(a, b, p.c));
                }
            }
            
            if (p.b > p.c) {
                // B -> C
                int b = p.b - p.c;
                int c = p.c * 2;
                if (!vis[p.a][b]) {
                    vis[p.a][b] = true;
                    que.offer(new P(p.a, b, c));
                }
            } else if (p.c > p.b) {
                // C -> B
                int c = p.c - p.b;
                int b = p.b * 2;
                if (!vis[p.a][b]) {
                    vis[p.a][b] = true;
                    que.offer(new P(p.a, b, c));
                }
            }
            
            if (p.a > p.c) {
                // A -> C
                int a = p.a - p.c;
                int c = p.c * 2;
                if (!vis[a][p.b]) {
                    vis[a][p.b] = true;
                    que.offer(new P(a, p.b, c));
                }
            } else if (p.c > p.a) {
                // C -> A
                int c = p.c - p.a;
                int a = p.a * 2;
                if (!vis[a][p.b]) {
                    vis[a][p.b] = true;
                    que.offer(new P(a, p.b, c));
                }
            }
            
        }

        return 0;
    }
}