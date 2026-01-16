import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s;
    static char tmp, prev;
    static int cnt1, cnt2;
    static boolean isS1, isS2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        while(true) {
            s = br.readLine();
            if (s.equals("end")) break;

            isS1 = false; isS2 = false;
            cnt1 = 0; cnt2 = 0;
            prev = ' ';

            for (int i = 0; i < s.length(); i++) {
                tmp = s.charAt(i);
                // 모음
                if (list.contains(tmp)) {
                    if(!isS1) isS1 = true;
                    cnt1++;
                    cnt2 = 0;
                    
                // 자음
                } else {
                    cnt2++;
                    cnt1 = 0;
                }

                if (cnt1 >= 3 || cnt2 >= 3 || (prev == tmp && tmp != 'o' && tmp != 'e')) {
                    sb.append('<' + s + "> is not acceptable.\n");
                    isS2 = true;
                    break;
                }

                prev = tmp;
            }

            if (!isS2) {
                if (!isS1) sb.append('<' + s + "> is not acceptable.\n");
                else sb.append('<' + s + "> is acceptable.\n");
            }
        }
        
        System.out.println(sb.toString());
    }
}