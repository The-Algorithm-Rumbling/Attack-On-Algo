import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int [] start = {2, 3, 5, 7};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();

        for (int s : start) {
            dfs (0, s);
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth, int sum) {
        if (depth == n-1) {
            sb.append(sum).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int num = sum * 10 + i;
            if (isPrime(num)) dfs(depth+1, num);
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}