import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] ch;
    static int ans;
    static String quack = "quack";
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        ch = sc.next().toCharArray();

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            int idx = quack.indexOf(c);
            boolean isS = true;

            for (int j = 0; j < list.size(); j++) {
                int d = list.get(j);
                if ((d+1) % 5 == idx) {
                    list.set(j, (d+1) % 5);
                    isS = false;
                    break;
                }
            }

            if (isS) {
                if (idx == 0) list.add(0);
                else {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i : list) {
            if (i != 4) {
                System.out.println(-1);
                return;
            }
        }
        
        System.out.println(list.size());
    }
}