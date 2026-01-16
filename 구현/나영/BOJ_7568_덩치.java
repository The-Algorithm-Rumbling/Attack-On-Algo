import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int [][] p;
    static int [] score;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        score = new int [n];
        p = new int [n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < i; j++) {
                if (p[i][0] > p[j][0] && p[i][1] > p[j][1]) score[j]++;
                else if (p[j][0] > p[i][0] && p[j][1] > p[i][1]) score[i]++;
            }
        }
        
        for (int i : score) System.out.print((i+1) + " ");
    }
}