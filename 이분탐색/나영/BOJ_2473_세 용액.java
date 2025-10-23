import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static long min = Long.MAX_VALUE;
    static long [] solution;
    static int [] arr = new int [2], ans = new int [3];
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        solution = new long [n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution);

        dfs(0, 0);
        
        System.out.println(solution[ans[0]] + " " + solution[ans[1]] + " " + solution[ans[2]]);
    }

    static void dfs(int depth, int idx) {
        if (depth == 2) {
            binary();
            return;
        }

        if (idx >= n) return;

        dfs(depth, idx+1);

        arr[depth] = idx;

        dfs(depth+1, idx+1);
    }

    static void binary() {
        int left = arr[1] + 1;
        int right = n-1;

        long target = -(solution[arr[0]] + solution[arr[1]]);

        while(left <= right) {
            int mid = (left + right) / 2;

            long sum = solution[arr[0]] + solution[arr[1]] + solution[mid];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                ans[0] = arr[0];
                ans[1] = arr[1];
                ans[2] = mid;
            }

            if (sum > 0) right = mid - 1;
            else left = mid + 1;
        }
    }
}