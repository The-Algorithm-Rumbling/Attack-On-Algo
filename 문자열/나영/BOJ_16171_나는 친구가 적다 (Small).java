import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] charn;
    static int T, ans;
    static String s, keyword;
    // static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        s = sc.nextLine().replaceAll("[0-9]", "");
        keyword = sc.nextLine();

        /*
        for (int i = s.length()-1; i >= 0; i--) {
            if (Character.isDigit(s.charAt(i))) s = s.replace(s.charAt(i)+"", "");
        }
        */

        if (s.contains(keyword)) System.out.println(1);
        else System.out.println(0);
    }
}