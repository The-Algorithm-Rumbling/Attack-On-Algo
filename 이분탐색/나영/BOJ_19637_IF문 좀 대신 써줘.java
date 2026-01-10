import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String [] ss;
    static int [] arr;
    static int n, m, num;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ss = new String [n];
        arr = new int [n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ss[i] = st.nextToken();
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            num = Integer.parseInt(br.readLine());

            sb.append(ss[binary()]).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static int binary () {
        int l = 0;
        int r = arr.length;
        int idx = 0;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (num <= arr[mid]) {
                r = mid-1;
                idx = mid;
            } else l = mid+1;
        }

        return idx;
    }
}