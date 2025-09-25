import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, n, min, max;
    static char[][] ch;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        ch = new char [n][];

        for (int i = 0; i < n; i++) {
            ch[i] = br.readLine().toCharArray();
        }

        // a : 49 ~ 74
        // A : 17 ~ 42
        // 0 : 0 ~ 9
        
        Arrays.sort(ch, (a, b) -> {
            int idx1 = 0;
            int idx2 = 0;

            while (idx1 < a.length && idx2 < b.length) {
                char a1 = a[idx1];
                char b1 = b[idx2];

                if (a1 - '0' < 10 && b1 - '0' < 10) {
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();

                    boolean isS1 = a1 == '0' ? true : false;
                    boolean isS2 = b1 == '0' ? true : false;
                    int zeroCnt1 = 0;
                    int zeroCnt2 = 0;

                    while (idx1 < a.length && a[idx1] - '0' < 10) {
                        if (isS1 && a[idx1] == '0') {
                            zeroCnt1++;
                        } else {
                            if (isS1) isS1 = false;
                            sb1.append(a[idx1]);
                        }
                        idx1++;
                    }

                    while (idx2 < b.length && b[idx2] - '0' < 10) {
                        if (isS2 && b[idx2] == '0') {
                            zeroCnt2++;
                        } else {
                            if (isS2) isS2 = false;
                            sb2.append(b[idx2]);
                        }
                        idx2++;
                    }

                    String num1 = sb1.toString();
                    String num2 = sb2.toString();

                    if (num1.length() != num2.length()) return Integer.compare(num1.length(), num2.length());
                    
                    int tmp = num1.compareTo(num2);
                    if (tmp != 0) return tmp;
                    
                    if (zeroCnt1 != zeroCnt2) return Integer.compare(zeroCnt1, zeroCnt2);
                } else {
                    if (a1 != b1) {
                        if (a1 - '0' < 10 || b1 - '0' < 10) return a1 - b1;
                        else {
                            char upA = Character.toUpperCase(a1);
                            char upB = Character.toUpperCase(b1);

                            if (upA != upB) return upA - upB;
                            if (Character.isUpperCase(a1)) return -1;
                            else if (Character.isUpperCase(b1)) return 1;
                        }
                    }
                    idx1++;
                    idx2++;
                }
            }

            return Integer.compare(a.length, b.length);
        });
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ch[i].length; j++) {
                sb.append(ch[i][j]);
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }

}