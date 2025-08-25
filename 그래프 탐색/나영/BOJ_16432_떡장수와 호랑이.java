import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k;
    static boolean [][] arr;
    static boolean [][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new boolean [n][10];
        visited = new boolean [n][10];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int rice = Integer.parseInt(st.nextToken());
            for (int j = 0; j < rice; j++) {
                int a = Integer.parseInt(st.nextToken());
                arr[i][a] = true;
            }
        }

        if (bfs()) System.out.println(sb.toString());
        else System.out.println(-1);
    }

    static class P {
        int rice, cnt;
        List<Integer> list;

        P(int rice, int cnt, List<Integer> list) {
            this.rice = rice;
            this.cnt = cnt;
            this.list = list;
        }
    }

    static boolean bfs() {
        Queue<P> que = new LinkedList<>();
        que.offer(new P (0, 0, new ArrayList<>()));

        while(!que.isEmpty()) {
            P p = que.poll();

            if (p.cnt == n) {
                for (int i : p.list) {
                    sb.append(i).append("\n");
                }
                return true;
            }

            int rice = p.rice;

            if (visited[p.cnt][rice]) continue;

            for (int i = 1; i < 10; i++) {
                if (arr[p.cnt][i] && rice != i) {
                    List<Integer> list = new ArrayList<>(p.list);
                    list.add(i);
                    visited[p.cnt][rice] = true;
                    que.offer(new P (i, p.cnt+1, list));
                }
            }
        }

        return false;
    }
}