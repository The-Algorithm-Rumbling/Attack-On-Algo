import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, cntR, cntB, min=500000;
    static char [] ch;
    static boolean [] visR, visB;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        ch = br.readLine().toCharArray();
        visR = new boolean [ch.length];
        visB = new boolean [ch.length];

        for (int i = ch.length-2; i >= 0; i--) {
            if (ch[i] == 'R') {
                if (ch[i+1] == 'B' || visR[i+1]) {
                    cntR++;
                    visR[i] = true;
                }
            } else {
                if (ch[i+1] == 'R'|| visB[i+1]) {
                    cntB++;
                    visB[i] = true;
                }
            }
        }

        min = Math.min(cntR, cntB);

        cntR = 0; cntB = 0;
        visR = new boolean [ch.length];
        visB = new boolean [ch.length];

        for (int i = 1; i < ch.length; i++) {
            if (ch[i] == 'R') {
                if (ch[i-1] == 'B' || visR[i-1]) {
                    cntR++;
                    visR[i] = true;
                }
            } else {
                if (ch[i-1] == 'R'|| visB[i-1]) {
                    cntB++;
                    visB[i] = true;
                }
            }
        }
        
        min = Math.min(min, Math.min(cntR, cntB));
        
        System.out.println(min);
    }
}