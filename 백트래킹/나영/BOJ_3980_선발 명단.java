import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int C, max;
    static int [][] player;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        C = Integer.parseInt(br.readLine());
        for (int t = 0; t < C; t++) {
            player = new int [11][11];
            visited = new boolean [11];
            max = 0;
            for (int r = 0; r < 11; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 11; c++) {
                    player[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            System.out.println(dfs(0, 0));
        }
    }

    static int dfs(int r, int sum) {
        if (r == 11) {
            return sum;
        }

        for (int i = 0; i < 11; i++) {
            if(visited[i] || player[r][i] == 0) continue;
            visited[i] = true;
            max = Math.max(dfs(r + 1, sum + player[r][i]), max);
            visited[i] = false;
        }

        return max;
    }
}