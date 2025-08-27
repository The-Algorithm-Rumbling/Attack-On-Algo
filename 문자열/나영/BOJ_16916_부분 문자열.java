import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static String s, p;
    public static void main(String[] args) {
        s = sc.next();
        p = sc.next();

        int n = s.length();
        int m = p.length();

        int [] lps = computeLPS(p);

        int i = 0;
        int j = 0;

        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println(1);
                return;
            }

            if (i < n && s.charAt(i) != p.charAt(j)) {
                if (j != 0) {
                    j = lps[j-1];
                } else i++;
            }
        }
        
        System.out.println(0);
    }

    static int[] computeLPS(String p) {
        int m = p.length();
        int [] lps = new int[m];
        int length = 0;
        int i = 1;

        while (i < m) {
            if (p.charAt(i) == p.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) length = lps[length - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}