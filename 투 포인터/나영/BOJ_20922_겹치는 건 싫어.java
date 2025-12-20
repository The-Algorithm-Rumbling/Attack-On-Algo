import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, max, l, r, ans;
    static int [] arr, count;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        count = new int [max+1];

        while (r < n) {
            if (count[arr[r]] < k) {
                count[arr[r]]++;
                r++;
            } else {
                count[arr[l]]--;
                l++;
            }

            ans = Math.max(ans, r - l);
        }
        
        System.out.println(ans);
    }
}