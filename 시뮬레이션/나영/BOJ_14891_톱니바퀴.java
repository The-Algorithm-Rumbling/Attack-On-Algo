import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] map = new int [4][8];
    static StringTokenizer st;
    static boolean [] vis;
    static int k, ans;
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            char [] charn = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                map[i][j] = charn[j] - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            vis = new boolean [4];
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int R = Integer.parseInt(st.nextToken());

            turn(n, R);
        }

        sum();

        System.out.println(ans);
    }

    static void turn(int n, int R) {
        if (vis[n]) return;

        vis[n] = true;

        if (n != 3) {
            if (map[n][2] != map[n+1][6]) {
                turn(n+1, -R);
            }
        }

        if (n != 0) {
            if (map[n][6] != map[n-1][2]) {
                turn(n-1, -R);
            }
        }

        if (R == -1) {

            int tmp = map[n][7];
            map[n][7] = map[n][0];

            for (int i = 6; i >= 0; i--) {
                int tmp2 = map[n][i];
                map[n][i] = tmp;
                tmp = tmp2;
            }

        } else {
            int tmp = map[n][0];
            map[n][0] = map[n][7];

            for (int i = 1; i < 8; i++) {
                int tmp2 = map[n][i];
                map[n][i] = tmp;
                tmp = tmp2;
            }
        }
    }

    static void sum() {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            if (map[i][0] != 0) ans+=num;
            num*=2;
        }
    }
}