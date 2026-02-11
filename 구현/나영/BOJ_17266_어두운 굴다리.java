import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h=1;
    static int [] light;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        light = new int [m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            light[i] = Integer.parseInt(st.nextToken());

            if (m == 1) {
                h = Math.max(n - light[i], light[i] - 0);
                break;
            }

            if (i == 0) {
                h = Math.max(h, light[i] - 0);
            } else if (i == m-1) {
                h = Math.max(h, n - light[i]);
            } else {
                h = Math.max(h, (light[i] - light[i-1] + 1) / 2);
            }
        }
        
        System.out.println(h);
    }
}