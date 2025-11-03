import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int a, t, num, cnt, person, r=2;
    public static void main(String[] args) {
        a = sc.nextInt();
        t = sc.nextInt();
        num = sc.nextInt();

        while (true) {
            find();
        }
    }

    static void find () {
        for (int i = 0; i < 4; i++) {
            if (i % 2 == num) cnt++;
            if (cnt == t) {
                System.out.println(person % a);
                System.exit(0);
            }
            person++;
        }

        for (int i = 0; i < r; i++) {
            if (0 == num) cnt++;
            if (cnt == t) {
                System.out.println(person % a);
                System.exit(0);
            }
            person++;
        }

        for (int i = 0; i < r; i++) {
            if (1 == num) cnt++;
            if (cnt == t) {
                System.out.println(person % a);
                System.exit(0);
            }
            person++;
        }

        r++;
    }
}