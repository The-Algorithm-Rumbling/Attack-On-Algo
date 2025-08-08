import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
    static int [] arr1, arr2;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        arr1 = new int[n];
        arr2 = new int[4];
        
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int[n-1]);
        
        System.out.println(max + "\n" + min);
    }

    static void dfs(int cnt, int [] perm) {
        if (cnt == n-1) {
            int sum = arr1[0];
            for (int i = 1; i < n; i++) {
                sum = cal(sum, arr1[i], perm[i-1]);
            }
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (arr2[i] == 0) continue;
            arr2[i] = arr2[i] - 1;
            perm[cnt] = i;
            dfs(cnt + 1, perm);
            arr2[i] = arr2[i] + 1;
        }
    }

    static int cal(int sum, int a, int b) {
        if (b == 0) sum += a;
        else if (b == 1) sum -= a;
        else if (b == 2) sum *= a;
        else sum /= a;
        return sum;
    }
}