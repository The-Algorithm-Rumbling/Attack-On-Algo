import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, idx, tmp;
    static String s;
    static boolean [] arr = new boolean [26];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int t = 0; t < n; t++) {
            s = br.readLine();
            idx = -1;

            // 각 단어의 첫글자 단축어 여부 확인
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || s.charAt(i-1) == ' ') {
                    if (s.charAt(i) == ' ') continue;

                    tmp = Character.toUpperCase(s.charAt(i)) - 'A';
                    
                    if (arr[tmp]) continue;
                    
                    arr[tmp] = true;
                    idx = i;
                    break;
                }
            }

            // 단축어 지정 안 됐으면 모든 글자 다시 확인
            if (idx == -1) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') continue;

                    tmp = Character.toUpperCase(s.charAt(i)) - 'A';

                    if (arr[tmp]) continue;
                    
                    arr[tmp] = true;
                    idx = i;
                    break;
                }
            }

            for (int i = 0; i < s.length(); i++) {
                if (i == idx) sb.append("[");
                sb.append(s.charAt(i));
                if (i == idx) sb.append("]");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
}