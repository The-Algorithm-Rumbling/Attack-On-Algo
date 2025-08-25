import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int ans;
    static int [] arr = new int [10];
    static int [] arr2 = new int [10];
    static void dfs (int depth) {
        if (depth == 10) {
            int cnt = 0;
            for (int i = 0; i < 10; i++) {
                if (arr[i] == arr2[i]) cnt++;
            }

            if (cnt >= 5) ans++;
            return;
        }

        for (int i = 1; i < 6; i++) {
            if (depth >= 2 && (arr2[depth - 2] == i && arr2[depth - 1] == i)) continue;
            arr2[depth] = i;
            dfs(depth+1);
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        
        System.out.println(ans);
    }
}