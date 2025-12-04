import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,  l, r, mid, max, ans;
    static long m, sum;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int [n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }
        
        m = Integer.parseInt(br.readLine());

        if (sum <= m)  {
            System.out.println(max);
            return;
        }
        
        l = 0;
        r = max;

        while (l <= r) {
            mid = (l + r) / 2;
            sum = 0;
            for (int a : arr) {
                sum += Math.min(a, mid);
            }

            if (sum <= m) {
                ans = mid;
                l = mid+1;
            } else r = mid-1;
        }
        
        
        System.out.println(ans);
    }
}