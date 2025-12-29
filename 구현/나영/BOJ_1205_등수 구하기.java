import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, p, tmp, ans, max;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }
        
        arr = new int [n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;

            if (k < tmp) ans++;
        }

        Arrays.sort(arr);

        if (n < p ||arr[n-p] < k) System.out.println(ans+1);
        else System.out.println(-1);
    }
}