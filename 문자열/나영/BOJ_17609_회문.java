import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            sb.append(check(ch)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int check(char[] ch) {
        int l = 0, r = ch.length - 1;

        while (l < r) {
            if (ch[l] != ch[r]) {
                // 한 글자 제거 후 양쪽 검사
                if (find(ch, l + 1, r) || find(ch, l, r - 1)) {
                    return 1;
                } else {
                    return 2;
                }
            }
            l++;
            r--;
        }

        return 0;
    }

    static boolean find(char[] ch, int l, int r) {
        while (l < r) {
            if (ch[l] != ch[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}
