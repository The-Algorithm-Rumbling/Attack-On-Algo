import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static boolean isS;
    static boolean [] visited;
    static int [][] frog, flower;
    static StringBuilder sb = new StringBuilder();
    static List<int []> list [];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        frog = new int [n+1][4];
        flower = new int [n+1][2];
        visited = new boolean [n+1];
        list = new ArrayList[n+1];

        for (int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                frog[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                flower[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            list[a].add(new int[] {b, c});
            list[b].add(new int[] {a, c});
        }

        dfs(1, new int[n+1]);
        
        System.out.println(isS ? sb.toString() : "NO");
    }

    static void dfs(int cnt, int [] arr) {
        if (isS) return;
        if (cnt == n+1) {
            isS = true;
            sb.append("YES\n");
            for (int i = 1; i < n+1; i++) sb.append(arr[i]).append(" ");
            return;
        }

        for (int i = 1; i < n+1; i++) {
            if (visited[i]) continue;
            if (flower[i][0] != cnt && flower[i][1] != cnt) continue;

            boolean good = true;
            
            for (int [] ex : list[cnt]) {
                if (ex[0] < cnt) {
                    int prevFrog = frog[i][ex[1]];
                    int nextFrog = frog[arr[ex[0]]][ex[1]];
                    if (prevFrog != nextFrog) {
                        good = false;
                        break;
                    }
                }
            }

            if (good) {
                visited[i] = true;
                arr[cnt] = i;
                dfs(cnt + 1, arr);
                visited[i] = false;
            }
            
        }
    }
}