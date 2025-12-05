import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, ans;
    static boolean isS;
    static char [] ch;
    static String cur;
    static boolean [] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        vis = new boolean [n];
        ch = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            if (ch[i] == 'P') {
                isS = false;
                for (int j = i-k; j < i; j++) {
                    if (j < 0) continue;
                    if (ch[j] == 'H' && !vis[j]) {
                        vis[j] = true;
                        ans++;
                        isS= true;
                        break;
                    }
                }
                
                if (!isS) {
                    for (int j = i+1; j <= i+k; j++) {
                        if (j >= n) break;
                        if (ch[j] == 'H' && !vis[j]) {
                            vis[j] = true;
                            ans++;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
        
    }
}