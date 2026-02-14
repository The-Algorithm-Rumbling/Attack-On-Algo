import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, l, r, mid, min=2000000001, target, sum1, sum2, n1, n2;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int [n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n-1; i++) {
            find(i);
        }
        
        System.out.println(n1 + " " + n2);
    }

    static void find(int idx) {
        l = idx+1;
        r = n-1;
        target = -arr[idx];

        while(l <= r) {
            mid = (l + r) / 2;

            if (arr[mid] < target) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

        sum1 = 2000000001; sum2 = 2000000001;

        if (l < n) {
            sum1 = Math.abs(arr[l] + arr[idx]);
        }

        if (l-1 > idx) {
            sum2 = Math.abs(arr[l-1] + arr[idx]);
        }

        if (sum1 < min) {
            min = sum1;
            n1 = arr[idx];
            n2 = arr[l];
        }
        
        if (sum2 < min) {
            min = sum2;
            n1 = arr[idx];
            n2 = arr[l-1];
        }

    }
}