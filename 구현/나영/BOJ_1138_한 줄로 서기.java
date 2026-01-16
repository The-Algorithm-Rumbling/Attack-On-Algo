import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, p, cnt;
    static int [] line;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        line = new int [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            p = Integer.parseInt(st.nextToken());
            cnt = 0;
            for (int j = 0; j < n; j++) {
                if (line[j] == 0) {
                    if (cnt == p) {
                        line[j] = i;
                        break;
                    }
                    cnt++;
                }
            }
        }

        for (int h : line) sb.append(h).append(" ");
        
        System.out.println(sb);
    }
}