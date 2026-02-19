import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
    static int [] arr, count = new int [4];
    static boolean [] vis;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int [n];
        vis = new boolean [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int [n-1]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int [] total) {
        if (depth == n-1) {
            get(total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (count[i] == 0) continue;
            total[depth] = i;
            count[i]--;
            dfs(depth+1, total);
            count[i]++;
        }
    }

    static void get(int [] total) {
        int sum = arr[0];
        for (int i = 0; i < n-1; i++) {
            // 덧셈
            if (total[i] == 0) sum += arr[i+1];
            // 뺄셈
            else if (total[i] == 1) sum -= arr[i+1];
            // 곱셈
            else if (total[i] == 2) sum *= arr[i+1];
            // 나눗셈
            else sum /= arr[i+1];
        }

        max = Math.max(sum, max);
        min = Math.min(sum, min);
    }
}