import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, cnt;
    static boolean [] visited;
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean [101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder.put(a, b);
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            snake.put(a, b);
        }

        visited[1] = true;
        System.out.println(bfs());
    }

    static int bfs () {
        Queue <int[]> que = new LinkedList<>();
        que.offer(new int[] {1, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();
            if (q[0] == 100) return q[1];

            for (int i = 1; i <= 6; i++) {
                int nr = q[0] + i;
                if (nr > 100 || visited[nr]) continue;
                while(true) {
                    visited[nr] = true;
                    if (ladder.containsKey(nr)) {
                        nr = ladder.get(nr);
                    } else if (snake.containsKey(nr)) {
                        nr = snake.get(nr);
                    } else break;
                }

                que.offer(new int [] {nr, q[1] + 1});
            }
        }

        return 0;
    }
}