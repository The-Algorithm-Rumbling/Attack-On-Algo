import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, cnt;
    static char [][] charn;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        charn = new char [2][];


        for (int i = 0; i < 2; i++) {
            charn[i] = br.readLine().toCharArray();
        }
        
        System.out.println(bfs());
    }

    static int bfs () {
        Queue <int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0, 0});

        while (!que.isEmpty()) {
            int [] q = que.poll();
            if (q[2] >= n) return 0;

            if (cnt < q[2]) {
                charn[0][cnt] = '0';
                charn[1][cnt] = '0';
                cnt++;
            }

            int nc = q[1] + k;
            int nr = q[0] == 0 ? 1 : 0;

            if (nc >= n) return 1;
            if (charn[nr][nc] == '1') {
                charn[nr][nc] = '0';
                que.offer(new int [] {nr, nc, q[2] + 1});
            }

            nc = q[1] + 1;
            
            if (nc >= n) return 1;
            if (charn[q[0]][nc] == '1') {
                charn[q[0]][nc] = '0';
                que.offer(new int [] {q[0], nc, q[2] + 1});
            }

            nc = q[1] - 1;
            
            if (nc > cnt && charn[q[0]][nc] == '1') {
                charn[q[0]][nc] = '0';
                que.offer(new int [] {q[0], nc, q[2] + 1});
            }
        }

        return 0;
    }
}