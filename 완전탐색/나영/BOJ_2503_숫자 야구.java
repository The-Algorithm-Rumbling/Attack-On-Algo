import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, max;
    static boolean pos;
    static int [][] question;
    public static void main(String[] args) {
        
        n  = sc.nextInt();
        question = new int [n][3];

        for (int i = 0; i < n; i++) {
            question[i][0] = sc.nextInt();
            question[i][1] = sc.nextInt();
            question[i][2] = sc.nextInt();
        }

        for (int i = 123; i <= 987; i++) {
            if (!isValid(i)) continue;

            pos = true;

            for (int [] q : question) {
                if (!check(i, q[0], q[1], q[2])) {
                    pos = false;
                    break;
                }
            }

            if (pos) max++;
            
        }
        
        System.out.println(max);
    }

    static boolean isValid (int num) {
        int a = num / 100;
        int b = (num % 100) / 10;
        int c = num % 10;

        if (a == 0 || b == 0 || c == 0) return false;
        if (a == b || b == c || c == a) return false;

        return true;
    }

    static boolean check (int ex, int num, int strike, int ball) {
        int [] a1 = {ex / 100, (ex % 100) / 10, ex % 10};
        int [] a2 = {num / 100, (num % 100) / 10, num % 10};

        int s = 0; int b = 0;

        for (int i = 0; i < 3; i++) {    
            if (a1[i] == a2[i]) s++;
            else if (a1[i] == a2[0] || a1[i] == a2[1] || a1[i] == a2[2]) b++;
        }

        return s == strike && b == ball;
    }
}