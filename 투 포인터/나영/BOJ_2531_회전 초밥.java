import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, d, k, c, cnt, max;
    static int [] arr, selected;
    static boolean [] visR, visB;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int [n];
        selected = new int [d+1];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        selected[c] = 1;
        cnt = 1; max = 1;

        for (int i = 0; i < k; i++) {
            if (selected[arr[i]] == 0) {
                cnt++;
            }
            selected[arr[i]]++;
        }

        max = cnt;

        for (int i = 1; i < n; i++) {
            selected[arr[i-1]]--;
            if (selected[arr[i-1]] == 0) cnt--;
            selected[arr[(i+k-1)%n]]++;
            if (selected[arr[(i+k-1)%n]] == 1) cnt++;
            max = Math.max(max, cnt);
        }
        
        System.out.println(max);
    }
}