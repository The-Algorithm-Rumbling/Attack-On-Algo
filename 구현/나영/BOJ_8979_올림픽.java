import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, c, ans=1;
    static boolean isS;
    static int [][] country;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken())-1;
        country = new int [n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken())-1;

            for (int j = 0; j < 3; j++) {
                country[c][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == k) continue;

            // 금메달 비교
            if (country[i][0] < country[k][0]) continue;
            else if (country[i][0] > country[k][0]) {
                ans++;
                continue;
            }

            // 은메달 비교
            if (country[i][1] < country[k][1]) continue;
            else if (country[i][1] > country[k][1]) {
                ans++;
                continue;
            }
            
            // 동메달 비교
            if (country[i][2] > country[k][2]) ans++;
        }
        
        System.out.println(ans);
    }
}