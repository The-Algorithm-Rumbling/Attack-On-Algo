import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, idx;
    static long ans;
    static int [] oil, dist;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dist = new int [n-1];
        oil = new int [n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n-1; i++) dist[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) oil[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n-1; i++) {
            if (oil[idx] > oil[i]) idx = i;
            ans += (long) oil[idx] * dist[i];
        }
        
        System.out.println(ans);
    }
}