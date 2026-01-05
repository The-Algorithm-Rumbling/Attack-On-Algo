import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, x, max, days;
    static int [] arr, sum;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int [n];
        sum = new int [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (i < x) sum[i] = sum[i-1] + arr[i];
            else sum[i] = sum[i-1] + arr[i] -  arr[i-x];
            
            if (max < sum[i]) {
                max = sum[i];
                days = 1;
            } else if (max == sum[i]) days++;
        }
        
        if (max == 0) System.out.println("SAD");
        else System.out.println(max + "\n" + days);
    }
}