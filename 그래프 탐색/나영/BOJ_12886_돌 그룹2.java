import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int A, B, C, sum, max;
    static class P {
        int a, b;

        P(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = Math.max(A, Math.max(B, C));
        sum = A + B + C;

        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        boolean [][] vis = new boolean[1501][1501];
        Queue<P> que = new LinkedList<>();
        que.offer(new P(A, B));
        vis[A][B] = true;

        while(!que.isEmpty()) {
            P p = que.poll();
            int a = p.a; int b = p.b; int c = sum - (a + b);

            if (a == b && b == c) return 1;

            int [] arr = {a, b, c};

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i] > arr[j]) {
                        int[] next = arr.clone();
                        next[i] -= next[j];
                        next[j] *= 2;

                        int na = next[0]; int nb = next[1];

                        if(!vis[na][nb]) {
                            vis[na][nb] = true;
                            que.offer(new P(na, nb));
                        }
                    }
                }
            }
            
        }

        return 0;
    }
}