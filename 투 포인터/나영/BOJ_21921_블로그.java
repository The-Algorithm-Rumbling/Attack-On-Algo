import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, x, sum, days=1, max;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int [n];
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }

        max = sum;
        
        for (int i = x; i < n; i++) {
            sum += arr[i];
            sum -= arr[i-x];

            if (sum == max) days++;

            if (sum > max) {
                days = 1;
                max = sum;
            }
        }

        if (max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(days);
        }
        
    }
}