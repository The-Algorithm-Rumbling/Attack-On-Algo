import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, n;
    static long [] arr;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new long [n];

            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            long max = 0;
            long ans = 0;

            for (int i = n-1; i >= 0; i--) {
                if (arr[i] > max) max = arr[i];
                else ans += max - arr[i];
            }

            sb.append(ans).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}