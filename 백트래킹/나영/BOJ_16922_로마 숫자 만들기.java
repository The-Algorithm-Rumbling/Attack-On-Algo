import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int [] arr = {1, 5, 10, 50};
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dfs(0, 0, 0);
        
        System.out.println(set.size());
    }

    static void dfs(int start, int sum, int cnt) {
        if (cnt == n) {
            set.add(sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            dfs(i, sum + arr[i], cnt + 1);
        }
    }
}