import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, l, r, target, min=2000000001, n1, n2;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int [n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        r = n-1;

        while(l < r) {
            target = arr[l] + arr[r];

            if (min > Math.abs(target)) {
                min = Math.abs(target);
                n1 = arr[l];
                n2 = arr[r];
            }

            if (target > 0) {
                r--;
            } else if (target == 0) {
                break;
            } else {
                l++;
            }
        }
        
        System.out.println(n1 + " " + n2);
    }
}