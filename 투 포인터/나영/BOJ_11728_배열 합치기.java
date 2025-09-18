import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, p1, p2;
    static int [] a, b;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int [n];
        b = new int [m];
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        
        while(p1 < n && p2 < m) {
            if (a[p1] < b[p2]) {
                sb.append(a[p1++] + " ");
            } else {
                sb.append(b[p2++] + " ");
            }
        }

        if (p1 == n) {
            while (p2 < m) sb.append(b[p2++] + " ");
        } else while (p1 < n) sb.append(a[p1++] + " ");

        System.out.println(sb.toString());
        
    }
}