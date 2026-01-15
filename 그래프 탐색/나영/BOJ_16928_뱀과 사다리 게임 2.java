import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static boolean [] vis = new boolean[101];
    static Map<Integer, Integer> ladder, snake;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int [] {1, 0});
        vis[1] = true;

        while (!que.isEmpty()) {
            int [] q = que.poll();
            int tmp = q[0]; int cnt = q[1];

            if (tmp == 100) return cnt;

            for (int i = 1; i <= 6; i++) {
                int next = tmp + i;
                if (check(next)) {
                    if (ladder.containsKey(next)) {
                        next = ladder.get(next);
                    } else if (snake.containsKey(next)) {
                        next = snake.get(next);
                    }

                    if (vis[next]) continue;
                    vis[next] = true;
                    que.offer(new int [] {next, cnt+1});
                }
            }
        }
        
        return 0;
    }

    static boolean check(int r) {
        return r > 0 && r < 101;
    }
}