import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, n, sr, sc, er, ec;
    static int [][] map;
    static boolean [] vis;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int [n+1][2];
            st = new StringTokenizer(br.readLine());
            sr = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());
            map[n][0] = er; map[n][1] = ec;

            int dist = Math.abs(sr - er) + Math.abs(sc - ec);
            if (dist <= 20 * 50) {
                sb.append("happy\n");
                continue;
            }
            
            sb.append(bfs());
        }
        
        System.out.println(sb.toString());
    }

    static String bfs() {
        Queue<int []> que = new LinkedList<>();
        que.offer(new int []{sr, sc});
        vis = new boolean[n+1];

        while(!que.isEmpty()) {
            int [] q = que.poll();

            if (q[0] == er && q[1] == ec) return "happy\n";

            for (int i = 0; i < n+1; i++) {
                int dist = Math.abs(q[0] - map[i][0]) + Math.abs(q[1] - map[i][1]);
                if (!vis[i] && dist <= 1000) {
                    que.offer(new int [] {map[i][0], map[i][1]});
                    vis[i] = true;
                }
            }
        }

        return "sad\n";
    }
}