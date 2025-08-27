import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int L, C;
    static char [] arr, charn;
    static Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        L = sc.nextInt();
        C = sc.nextInt();

        charn = new char [L];
        arr = new char [C];

        for (int i = 0; i < C; i++) {
            arr[i] = sc.next().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(sb.toString());
    }

    static void dfs(int start, int cnt) {
        if (cnt == L) {
            check();
            return;
        }

        for (int i = start; i < C; i++) {
            charn[cnt] = arr[i];
            dfs(i + 1, cnt + 1);
        }
    }

    static void check() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < L; i++) {
            if (set.contains(charn[i])) a++;
            else b++;
        }

        if (a > 0 && b > 1) {
            for (int i = 0; i < L; i++) {
                sb.append(charn[i]);
            }
            sb.append("\n");
        }
    }
}