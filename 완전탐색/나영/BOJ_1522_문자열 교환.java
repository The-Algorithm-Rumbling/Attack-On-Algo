import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int cntA, cntB, min = 1000;
    static char [] ch;
    public static void main(String[] args) {
        ch = sc.next().toCharArray();

        for (char c : ch) {
            if (c == 'a') cntA++;
        }

        for (int i = 0; i < cntA; i++) {
            if (ch[i] == 'b') cntB++;
        }

        min = cntB;
        for (int i = 1; i < ch.length; i++) {
            if (ch[i-1] == 'b') cntB--;
            if (ch[(i+cntA-1) % ch.length] == 'b') cntB++;
            min = Math.min(min, cntB);
        }
        
        System.out.println(min);
    }
}