import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [][] charns = new char [5][];
    static int max, ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            charns[i] = sc.nextLine().toCharArray();
            max = Math.max(max, charns[i].length);
        }

        for (int c = 0; c < max; c++) {
            for (int r = 0; r < 5; r++) {
                if (charns[r].length <= c) continue;
                sb.append(charns[r][c]);
            }
        }

        System.out.println(sb.toString());
    }
}