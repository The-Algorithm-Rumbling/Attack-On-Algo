import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, x, y;
    static int prev[];
    static char charn[];
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            visited = new boolean [10000];
            prev = new int [10000];
            charn = new char [10000];
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            visited[x] = true;
            prev[x] = -1;

            bfs();
        }
        
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);

        while (!que.isEmpty()) {
            int n = que.poll();

            if (n == y) {
                sb.append(find(n)).append("\n");
                return;
            }

            int nr = n*2 % 10000;
            if (!visited[nr]) {
                prev[nr] = n;
                charn[nr] = 'D';
                visited[nr] = true;
                que.offer(nr);
            }

            nr = n - 1 < 0 ? 9999 : n - 1;
            if (!visited[nr]) {
                prev[nr] = n;
                charn[nr] = 'S';
                visited[nr] = true;
                que.offer(nr);
            }

            int n4 = n / 1000;
            nr = (n % 1000) * 10 + n4;
            if (!visited[nr]) {
                prev[nr] = n;
                charn[nr] = 'L';
                visited[nr] = true;
                que.offer(nr);
            }

            int n1 = n % 10;
            int nn = n / 10;
            nr = nn + n1 * 1000;
            if (!visited[nr]) {
                prev[nr] = n;
                charn[nr] = 'R';
                visited[nr] = true;
                que.offer(nr);
            }

        }
    }

    static String find(int n) {
        StringBuilder path = new StringBuilder();
        while (prev[n] != -1) {
            path.append(charn[n]);
            n = prev[n];
        }
        
        return path.reverse().toString();
    }
}