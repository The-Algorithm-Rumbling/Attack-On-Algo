import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static String s = "";
    public static void main(String[] args) {
        n = sc.nextInt();
        dfs(s);
    }

    static void dfs(String st) {
        if (st.length() == n) {
            System.out.println(st);
            System.exit(0);
        }

        for (int i = 1; i < 4; i++) {
            if (check(st + i)) dfs(st + i);
        }
    } 

    static boolean check(String st) {
        for (int i = 1; i <= st.length() / 2; i++) {
            String front = st.substring(st.length() - i*2, st.length()-i);
            String back = st.substring(st.length() - i, st.length());
            if (front.equals(back)) return false;
        }
        return true;
    }
}