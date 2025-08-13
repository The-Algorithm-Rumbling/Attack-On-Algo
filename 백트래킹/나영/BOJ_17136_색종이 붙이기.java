import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [] arr = {5, 5, 5, 5, 5};
    static int [][] map = new int[10][10];
    static boolean [][] visited = new boolean [10][10];;
    static int ans = Integer.MAX_VALUE;
    static List<int[]> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for (int r = 0; r < 10; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) list.add(new int [] {r, c});
            }
        }

        dfs(0, 0, arr);

        System.out.println(ans == Integer.MAX_VALUE?-1:ans);
        
    }

    static void dfs(int idx, int cnt, int [] ones) {
        if (cnt >= ans) return;
        if (idx == list.size()) {
            ans = Math.min(ans, cnt);
            return;
        }

        int r = list.get(idx)[0];
        int c = list.get(idx)[1];
        if (visited[r][c]) {
            dfs(idx + 1, cnt, ones);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (ones[i] == 0) continue;
            Set<int[]> set = fill(r, c, i+1);

            if (set != null) {
                for (int [] arr : set) {
                    visited[arr[0]][arr[1]] = true;
                }
                
                ones[i]--;
                dfs(idx + 1, cnt + 1, ones);
                ones[i]++;
                
                for (int [] arr : set) {
                    visited[arr[0]][arr[1]] = false;
                }
            }
        }
    }

    static Set<int []> fill(int r, int c, int num) {
        Set<int[]> set = new HashSet<>();
        for (int i = r; i < num + r; i++) {
            for (int j = c; j < num + c; j++) {
                if (!check(i, j) || map[i][j] == 0) return null;
                set.add(new int[] {i, j});
            }
        }

        if (set.size() == num * num) {
            return set;
        }

        return null;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < 10 && c >= 0 && c < 10 && !visited[r][c];
    }
}