import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, cnt, ans;
    static char c;
    static String s;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = st.nextToken().charAt(0);

        if (c == 'Y') k = 1;
        else if (c =='F') k = 2;
        else k = 3;

        for (int t = 0; t < n; t++) {
            s = br.readLine();
            if (!set.contains(s)) {
                cnt++;
                set.add(s);
            }

            // k명의 사람을 모았는지 확인
            if (cnt == k) {
                cnt = 0;
                ans++;
            }
        }
        
        System.out.println(ans);
    }
}